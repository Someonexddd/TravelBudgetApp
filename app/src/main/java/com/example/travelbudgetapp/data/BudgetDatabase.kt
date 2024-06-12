package com.example.travelbudgetapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.travelbudgetapp.data.dao.ExpenseDao

@Database(
    entities = [Expense::class],
    version = 1
)
abstract class BudgetDatabase : RoomDatabase() {
    abstract val expenseDao : ExpenseDao
}