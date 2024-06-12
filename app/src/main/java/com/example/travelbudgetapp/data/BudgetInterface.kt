package com.example.travelbudgetapp.data


sealed interface BudgetInterface {
    object SaveExpense: BudgetInterface
    data class setCategory(val category: String) : BudgetInterface
    data class setAmount(val amount: Double) : BudgetInterface
    data class setDescription(val description: String) : BudgetInterface
    data class sortExpenses(val sortType: ExpenseSortType) : BudgetInterface

    data class deleteExpense(val expense: Expense) : BudgetInterface



}