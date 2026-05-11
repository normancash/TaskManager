package com.example.taskmanager.ui.navigation
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.ui.repository.TaskRepository


@Composable
fun AppNavigation(repository: TaskRepository, modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = List) {
        composable<List> {
            TaskListScreen(navController,repository)
        }
        composable<Add> {
            AddTaskScreen(navController,repository)
        }
    }

}