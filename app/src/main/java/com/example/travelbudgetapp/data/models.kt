package com.example.travelbudgetapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*


data class CategoryBudget(
    val category: String,
    val budget: Double,
    var amountSpent: Double = 0.0,
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
)
@Entity
data class Expense(
    val category: String,
    val amount: Double,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
)
