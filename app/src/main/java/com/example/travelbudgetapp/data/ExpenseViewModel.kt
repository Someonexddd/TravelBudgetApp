package com.example.travelbudgetapp.data

import com.example.travelbudgetapp.data.dao.ExpenseDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class ExpenseViewModel(
    private val dao: ExpenseDao
) : ViewModel() {

    private val _sortType = MutableStateFlow(ExpenseSortType.DATE)
    private val _expenses = _sortType
        .flatMapLatest { sortType ->
            when (sortType) {
                ExpenseSortType.DATE -> dao.getAllExpenses()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(ExpenseState())
    val state = combine(_state, _sortType, _expenses) { state, sortType, expenses ->
        state.copy(
            expenses = expenses,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ExpenseState())

    fun onEvent(event: BudgetInterface) {
        when (event) {
            BudgetInterface.SaveExpense -> {
                val category = state.value.category
                val amount = state.value.amount
                val description = state.value.description

                if(category.isBlank() || amount == 0.0 || description.isBlank()) {
                    return
                }

                val expense = Expense(
                    category = category,
                    amount = amount.toDouble(),
                    description = description,
                )
                viewModelScope.launch {
                    dao.upsertExpense(expense)
                }
                _state.update { it.copy(
                    isAddingExpense = false,
                    category = "",
                    amount = 0.0,
                    description = ""
                ) }
            }
            is BudgetInterface.deleteExpense -> {
                viewModelScope.launch { dao.deleteExpense(event.expense) }
            }
            is BudgetInterface.setAmount -> {
                _state.update { it.copy(amount = event.amount) }
            }
            is BudgetInterface.setCategory -> {
                _state.update { it.copy(category = event.category) }
            }
            is BudgetInterface.setDescription -> {
                _state.update { it.copy(description = event.description) }
            }
            is BudgetInterface.sortExpenses -> {
                _sortType.value = event.sortType
            }
        }
    }
}
