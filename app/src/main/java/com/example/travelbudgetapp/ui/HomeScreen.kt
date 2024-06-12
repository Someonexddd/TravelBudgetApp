package com.example.travelbudgetapp.ui



import com.example.travelbudgetapp.data.Expense
import com.example.travelbudgetapp.data.ExpenseState


import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.travelbudgetapp.data.BudgetInterface
import androidx.navigation.compose.NavHost

@Composable
fun HomeScreen(
    state: ExpenseState,
    onEvent: (BudgetInterface) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(state.expenses) {expense ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${expense.category} ${expense.amount} ${expense.description}"
                    )
                    IconButton(onClick = {
                        onEvent(BudgetInterface.deleteExpense(expense))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete contact"
                        )
                    }
                }
            }
        }
    }
}
