package com.example.travelbudgetapp.ui

import androidx.compose.foundation.layout.* // For layout components like Column, Row, Spacer, etc.
import androidx.compose.foundation.text.KeyboardOptions // For keyboard options in TextField
import androidx.compose.material.* // For Material components like Text, TextField, Button, etc.
import androidx.compose.runtime.* // For state management in Compose
import androidx.compose.ui.Modifier // For modifiers
import androidx.compose.ui.text.input.KeyboardType // For specifying keyboard types in TextField
import androidx.compose.ui.unit.dp // For specifying dimensions
import com.example.travelbudgetapp.data.Expense // Your Expense data class
import com.example.travelbudgetapp.data.CategoryBudget // Your CategoryBudget data class
import androidx.compose.ui.Alignment // For alignment options
import android.content.Context // For context if needed
import androidx.compose.ui.focus.FocusRequester // For focus management in TextField
import androidx.compose.ui.focus.focusRequester // For setting focus requester
import androidx.compose.ui.focus.onFocusChanged // For handling focus change events
import androidx.navigation.compose.NavHost // For navigation
import androidx.navigation.compose.composable // For navigation composable
import androidx.navigation.compose.rememberNavController // For navigation controller
import com.example.travelbudgetapp.data.ExpenseRepository // Your ExpenseRepository class
import com.example.travelbudgetapp.utils.exportToCSV // Assuming you have a utility function for CSV export


@Composable
fun AddCategoryScreen(
    onAddCategory: (CategoryBudget) -> Unit,
    onBack: () -> Unit
) {
    var categoryName by remember { mutableStateOf("") }
    var dailyBudget by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            label = { Text("Category Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = dailyBudget,
            onValueChange = { dailyBudget = it },
            label = { Text("Daily Budget") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val budget = dailyBudget.toDoubleOrNull() ?: 0.0
                val category = CategoryBudget(category = categoryName, budget = budget)
                onAddCategory(category)
                categoryName = ""
                dailyBudget = ""
                onBack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Category")
        }
    }
}
