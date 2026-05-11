package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.taskmanager.ui.dao.AppDataBase
import com.example.taskmanager.ui.navigation.AppNavigation
import com.example.taskmanager.ui.repository.TaskRepository
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {

    private lateinit var db : AppDataBase
    private lateinit var repository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                db = Room.databaseBuilder(
                    applicationContext,
                    AppDataBase::class.java,
                    "tasks.db"
                ).build()
                repository = TaskRepository(db.taskDao())

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(repository,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
