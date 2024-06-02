package com.example.travelbudgetapp.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelbudgetapp.data.ExpenseRepository
import com.example.travelbudgetapp.utils.exportToCSV

@Composable
fun TravelBudgetApp(context: Context) {
    val navController = rememberNavController()
    val expenseRepository = remember { ExpenseRepository(0.0) }

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                categories = expenseRepository.getCategories(),
                onAddExpense = { navController.navigate("addExpense") },
                onExport = { exportToCSV(context, expenseRepository.getExpenses()) },
                onAddCategory = { navController.navigate("addCategory") },
            )
        }
        composable("addExpense") {
            AddExpenseScreen(
                categories = expenseRepository.getCategories(),
                expenseRepository = expenseRepository,
                onAddExpense = { expense ->
                    expenseRepository.addExpense(expense)
                    navController.popBackStack()
                },
                onBack = { navController.popBackStack() }
            )
        }

    }
}
