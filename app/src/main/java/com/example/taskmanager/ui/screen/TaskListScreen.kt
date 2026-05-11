package com.example.taskmanager.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.ui.model.Task
import com.example.taskmanager.ui.navigation.Add
import com.example.taskmanager.ui.repository.TaskRepository
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskListScreen(navController: NavController, repo: TaskRepository) {

    var tasks by remember { mutableStateOf(listOf<Task>()) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        tasks = repo.getTask()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Add)
            }) {
                Text("+")
            }
        }
    ) {
        LazyColumn {
            items(tasks) { task ->

                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {

                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Column {
                            Text(task.title)
                            Text(task.description)
                        }

                        Row {

                            Checkbox(
                                checked = task.isCompleted,
                                onCheckedChange = {
                                    scope.launch {
                                        repo.updateTask(
                                            task.copy(isCompleted = !task.isCompleted)
                                        )
                                        tasks = repo.getTask()
                                    }
                                }
                            )

                            IconButton(onClick = {
                                scope.launch {
                                    repo.deleteTask(task)
                                    tasks = repo.getTask()
                                }
                            }) {
                                Text("X")
                            }
                        }
                    }
                }
            }
        }
    }
}