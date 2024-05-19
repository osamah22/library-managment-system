package com.android.libraryManagementSystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

@Composable
fun AddBookScreen(booksViewModel: BooksViewModel) {
    var state by remember { mutableStateOf(1) }
    var bookName by remember { mutableStateOf("") }
    var numberOfPages by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Column (){
            Row {
                RadioButton(
                    selected = state == 1,
                    onClick = { state = 1 }
                )
                Column (
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                Text(text = "Wants to Read")
                }
            }
            Row {
                RadioButton(
                    selected = state == 2,
                    onClick = { state = 2 }
                )
                Column (
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
                    Text(text = "Reading")
                }
            }
            Row {
                RadioButton(
                    selected = state == 3,
                    onClick = { state = 3 }
                )
                Column (
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center){
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
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if(bookName.trim().isEmpty()){
                Toast.makeText(booksViewModel.context, "Book title is not valid", Toast.LENGTH_SHORT).show()
                return@Button
            }
            val book = Book(
                state = state,
                bookName = bookName.trim(),
                numberOfPages = numberOfPages.trim().toIntOrNull()?: 0,
                description = description.trim().ifEmpty { null })
            booksViewModel.addBook(book)
            Toast.makeText(booksViewModel.context, "Book added successfully!", Toast.LENGTH_SHORT).show()
        }
        ) {
            Text("Add Book" )
        }
    }
}