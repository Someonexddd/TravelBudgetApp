package com.example.travelbudgetapp.utils

import android.content.Context
import android.os.Environment
import com.example.travelbudgetapp.data.Expense
import java.io.File
import java.io.FileWriter

fun exportToCSV(context: Context, expenses: List<Expense>) {
    val csvFile = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "expenses.csv")
    val writer = FileWriter(csvFile)
    writer.append("Category,Amount,Description,Date\n")
    expenses.forEach { expense ->
        writer.append("${expense.category},${expense.amount},${expense.description ?: ""},${expense.date}\n")
    }
    writer.flush()
    writer.close()
}
