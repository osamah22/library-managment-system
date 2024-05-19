package com.android.libraryManagementSystem

import kotlinx.coroutines.flow.Flow
import androidx.compose.runtime.State

interface BooksRepository {
    fun getAllBooksStream(state: Int): Flow<List<Book>>
    fun getBookById(id: Int): Flow<List<Book>>
    suspend fun insertBook(book: Book)
    suspend fun updateBook(book: Book)
    suspend fun deleteBook(book: Book)
}
class OfflineBooksRepository(private val bookDao: BookDao):
    BooksRepository {
    override fun getAllBooksStream(state: Int): Flow<List<Book>>{
        return bookDao.getBooks(state)
    }

    override fun getBookById(id: Int): Flow<List<Book>> {
        return bookDao.getBookById(id)
    }

    override suspend fun insertBook(book: Book) {
        bookDao.addBook(book)
    }

    override suspend fun updateBook(book: Book) {
        bookDao.updateBook(book)
    }

    override suspend fun deleteBook(book: Book) {
        bookDao.deleteBook(book)
    }
}

