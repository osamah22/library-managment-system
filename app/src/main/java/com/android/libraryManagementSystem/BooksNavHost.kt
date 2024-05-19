package com.android.libraryManagementSystem

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

@Composable
fun BooksNavHost(
    booksViewModel: BooksViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController = navController,
        startDestination="HS",
        modifier = modifier){
        composable(route="HS"){
            HomeScreen(booksViewModel = booksViewModel,
                navHost = navController,
                nextDestination = NavDestination("BooksScreen")
            )
        }
        composable(route = "BooksScreen"){
            booksScreen(booksViewModel = booksViewModel, navHostController = navController,
                editBookDestination = NavDestination("EditBookScreen"))
        }
        composable(route = "ABScreen"){
            AddBookScreen(booksViewModel)
        }
        composable(route = "EditBookScreen"){
            EditBookScreen(booksViewModel)
        }

    }
}

@Composable
fun EditBookScreen(booksViewModel: BooksViewModel) {
    val book = booksViewModel.selectedBook.collectAsState(initial = listOf()).value.forEach { book ->
        Text(text = book.bookName)
        var state by remember { mutableIntStateOf(book.state) }
        var bookName by remember { mutableStateOf(book.bookName) }
        var numberOfPages by remember { mutableStateOf(book.numberOfPages.toString()) }
        var description by remember { mutableStateOf(book.description) }
        Column(modifier = Modifier.padding(16.dp)) {
            Column() {
                Row {
                    RadioButton(
                        selected = state == 1,
                        onClick = { state = 1 }
                    )
                    Column(
                        modifier = Modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Wants to Read")
                    }
                }
                Row {
                    RadioButton(
                        selected = state == 2,
                        onClick = { state = 2 }
                    )
                    Column(
                        modifier = Modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Reading")
                    }
                }
                Row {
                    RadioButton(
                        selected = state == 3,
                        onClick = { state = 3 }
                    )
                    Column(
                        modifier = Modifier.padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Read")
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = bookName,
                onValueChange = { bookName = it },
                label = { Text("Book Name") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = numberOfPages,
                onValueChange = { numberOfPages = it },
                label = { Text("Number of Pages") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            description?.let { it1 ->
                OutlinedTextField(
                    value = it1,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                if(bookName.trim().isEmpty()){
                    Toast.makeText(booksViewModel.context, "Book title is not valid", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                val newBook = Book(
                    id = book.id,
                    state = state,
                    bookName = bookName.trim(),
                    numberOfPages = numberOfPages.trim().toIntOrNull()?: 0,
                    description = description?.trim()?.ifEmpty { null })
                booksViewModel.update(newBook)
                Toast.makeText(booksViewModel.context, "Changes applied successfully", Toast.LENGTH_SHORT).show()
            }
            ) {
                Text("Apply changes Book" )
            }
        }
    }



}



