package com.example.travelbudgetapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelbudgetapp.data.CategoryBudget

@Composable
fun HomeScreen(
    categories: List<CategoryBudget>,
    onAddExpense: () -> Unit,
    onExport: () -> Unit,

    onAddCategory: () -> Unit // Added parameter
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Trip Budget Tracker", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        categories.forEach { category ->
            BudgetItem(category)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddExpense) {
            Text("Add Expense")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onExport) {
            Text("Export to CSV")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onAddCategory) { // Added button
            Text("Add Category")
        }
    }
}

@Composable
fun BudgetItem(category: CategoryBudget) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("${category.category}: ${category.amountSpent} / ${category.budget}")
        LinearProgressIndicator(progress = (category.amountSpent / category.budget).toFloat())
    }
}
