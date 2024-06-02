package com.example.travelbudgetapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*


data class CategoryBudget(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val category: String,
    val budget: Double,
    var amountSpent: Double = 0.0
)
@Entity
data class Expense(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    val category: String,
    val amount: Double,
    val description: String? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
