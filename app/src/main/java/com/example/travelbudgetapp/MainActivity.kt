package com.example.travelbudgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelbudgetapp.data.CategoryBudget
import com.example.travelbudgetapp.data.ExpenseRepository
import com.example.travelbudgetapp.ui.AddCategoryScreen
import com.example.travelbudgetapp.ui.AddExpenseScreen
import com.example.travelbudgetapp.ui.HomeScreen
import kotlin.math.exp

class MainActivity : ComponentActivity() {
    private val repository = ExpenseRepository(1000.0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                var screen by remember { mutableStateOf("home") }

                when (screen) {
                    "home" -> HomeScreen(
                        categories = repository.getCategories(),
                        onAddExpense = { screen = "addExpense" },
                        onExport = { /* Handle export */ },
                        onAddCategory = { screen = "addCategory" }, // Navigate to add category screen
                    )
                    "addCategory" -> AddCategoryScreen(
                        onAddCategory = {
                            repository.addCategory(it)
                            screen = "home"
                        },
                        onBack = { screen = "home" } // Navigate back to home screen
                    )
                    "addExpense" -> AddExpenseScreen(
                        categories = repository.getCategories().map { it },
                        expenseRepository = repository,
                        onAddExpense = {
                            repository.addExpense(it)
                            screen = "home"
                        },
                        onBack = { screen = "home" } // Navigate back to home screen
                    )
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    MaterialTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        HomeScreen(
            categories = listOf(),
            onAddExpense = {},
            onExport = {},
            onAddCategory = {},
        )
    }
}
