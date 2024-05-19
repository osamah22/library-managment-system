package com.android.libraryManagementSystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.android.libraryManagementSystem.ui.theme.RoomFriAfternoonTheme
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //no singleton design pattern (yet)
        val db = Room
            .databaseBuilder(applicationContext, Database::class.java,
                "libraryDB")
            .build()
        // 2. Manual BooksViewModel Creation
        val bookViewModel = BooksViewModel(
            OfflineBooksRepository(db.bookDao()),
            ioDispatcher = Dispatchers.IO,
            context = this)

        setContent {
            RoomFriAfternoonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StartApp(booksViewModel = bookViewModel)
                }
            }
        }
    }
}
@Composable
fun StartApp(booksViewModel: BooksViewModel){
    BooksNavHost(booksViewModel= booksViewModel, navController= rememberNavController())
}
