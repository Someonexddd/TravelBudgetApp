package com.example.travelbudgetapp.data

data class ExpenseState(
    val category: String = "",
    val amount: Double = 0.0,
    val description: String = "",
    val expenses: List<Expense> = emptyList(),
    val isAddingExpense: Boolean = false,
    val sortType: ExpenseSortType = ExpenseSortType.DATE
)