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
import androidx.compose.foundation.clickable // Import clickable from foundation package'




@Composable
fun AddExpenseScreen(
    categories: List<CategoryBudget>,
    expenseRepository: ExpenseRepository,
    onAddExpense: (Expense) -> Unit,
    onBack: () -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<CategoryBudget?>(null) }
    var description by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Add Expense", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

// DropdownMenu
        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = selectedCategory?.category ?: "Select Category",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = { expanded = true })
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCategory = category
                            expanded = false
                        }
                    ) {
                        Text(text = category.category)
                    }
                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (amount.isNotEmpty() && selectedCategory != null) {
                    val expense = Expense(
                        selectedCategory!!.category,
                        amount.toDouble(),
                        description
                    )
                    expenseRepository.addExpense(expense)
                    onBack()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Add Expense")
        }
    }
}
