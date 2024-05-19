package com.android.libraryManagementSystem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController

@Composable
fun booksScreen(
    modifier: Modifier = Modifier,
    booksViewModel: BooksViewModel,
    navHostController: NavHostController,
    editBookDestination: NavDestination
) {
    val books = booksViewModel.books.collectAsState(initial = listOf()).value

    LazyColumn(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(15.dp)
    ){
        item { 
            Text(text = "${books.size} books found")
        }
        items(books){
            book -> showBook(book = book, booksViewModel = booksViewModel,
            onEditClick = {navHostController.navigate(editBookDestination.navigatorName)})
        }
    }
}

@Composable
fun showBook(book: Book, booksViewModel: BooksViewModel, modifier: Modifier = Modifier, onEditClick: () -> Unit){
    Row (modifier = Modifier){
        Column (
            Modifier
                .background(Color(255, 235, 205))
                .border(1.dp, Color.Gray)
                .padding(5.dp),
        ) {
            Text(text = "Title: ${book.bookName}")
            Text(text = "Description: ${book.description ?: "No description"}" )
            Text(text = "pages: ${book.numberOfPages ?: "0"} page")
            Row (modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = { run { booksViewModel.setBookId( book.id) }; onEditClick()},
                    modifier = Modifier.fillMaxWidth(0.4f),
                ) {
                    Text(text = "Edit")
                }
                Button(
                    onClick = { booksViewModel.deleteBook(book) },
                    modifier = Modifier.fillMaxWidth(0.5f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text(text = "Delete", modifier = Modifier)
                }
            }

        }
    }
    Spacer(modifier = Modifier.height(10.dp))


}


