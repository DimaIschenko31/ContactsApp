package com.example.contactsapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.contactsapp.viewmodel.ContactViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel: ContactViewModel = viewModel()

    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ContactsListScreen(
                viewModel = viewModel,
                onAddClick = { navController.navigate("add") },
                onContactClick = { id -> navController.navigate("details/$id") }
            )
        }
        composable("add") {
            AddContactScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "details/{contactId}",
            arguments = listOf(navArgument("contactId") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("contactId") ?: return@composable
            DetailsContactScreen(
                contactId = id,
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onDeleted = { navController.popBackStack() }
            )
        }
    }
}