package com.android.libraryManagementSystem


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf

class BooksViewModel(

    // ViewModel Initialization
    private val repository: BooksRepository,
    private val ioDispatcher: CoroutineDispatcher,
    val context: Context
) : ViewModel() {

    // state of the book. Here is the states map -> {1: wants to read}, {2: reading}, {3: read}
    private val _state = mutableIntStateOf(0)
    private val booksState : State<Int> get() = _state
    // People Data Flow
    var books = repository.getAllBooksStream(booksState.value)

    var selectedBookId = mutableIntStateOf(0)
    private val _selectedBookState : State<Int> get() = selectedBookId
    var selectedBook = repository.getBookById(_selectedBookState.value)
    fun setBookId(id: Int){
        selectedBook = repository.getBookById(id)
    }
    fun setState(state: Int){
        when(state){
            1 -> _state.intValue = 1
            2 -> _state.intValue = 2
            3 -> _state.intValue = 3
            else -> throw IllegalArgumentException("State Does Not Exit")
        }
        books = repository.getAllBooksStream(booksState.value)
    }

    fun addBook(book: Book) =
        viewModelScope.launch(ioDispatcher)
        { repository.insertBook(book)}

    // remove Book to the books table
    fun deleteBook(book: Book) =
        viewModelScope.launch(ioDispatcher)
        { repository.deleteBook(book)}

    // update Book in the books table
    fun update(book: Book) =
        viewModelScope.launch(ioDispatcher)
        { repository.updateBook(book)}
}