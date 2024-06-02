package com.example.travelbudgetapp.data

data class CategoryBudget(
    val category: String,
    val budget: Double,
    var amountSpent: Double = 0.0
)

data class Expense(
    val category: String,
    val amount: Double,
    val description: String? = null,
    val date: Long = System.currentTimeMillis()
)
