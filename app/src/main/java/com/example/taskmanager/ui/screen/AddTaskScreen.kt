package com.example.taskmanager.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.taskmanager.ui.model.Task
import com.example.taskmanager.ui.repository.TaskRepository
import kotlinx.coroutines.launch

@Composable
fun AddTaskScreen(navController: NavController, repo: TaskRepository) {

    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") }
        )

        TextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("Descripción") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                repo.insertTask(Task(
                      title = title
                    , description = desc
                    , isCompleted = false))
                navController.popBackStack()
            }
        }) {
            Text("Guardar")
        }
    }
}