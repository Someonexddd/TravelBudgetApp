package com.example.travelbudgetapp.data


class ExpenseRepository(private val initialBudget: Double) {
    private var totalBudget: Double = initialBudget
    private val categories = mutableListOf<CategoryBudget>()
    private val expenses = mutableListOf<Expense>()

    fun getCategories(): List<CategoryBudget> = categories

    fun getExpenses(): List<Expense> = expenses

    fun addExpense(expense: Expense) {
        expenses.add(expense)
        categories.find { it.category == expense.category }?.let {
            it.amountSpent += expense.amount
        }
    }

    fun addCategory(category: CategoryBudget) {
        categories.add(category)
    }

    fun getTotalBudget(): Double = totalBudget

    fun setTotalBudget(newBudget: Double) {
        totalBudget = newBudget
    }

    fun getExpensesForCategory(categoryName: String): List<Expense> {
        // Filter expenses based on the specified category name
        return expenses.filter { it.category == categoryName }
    }
}
