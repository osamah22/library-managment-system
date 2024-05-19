package com.android.libraryManagementSystem
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("select * from books where state = :state")
    fun getBooks(state: Int): Flow<List<Book>>

    @Query("select count(id) from books")
    fun getBookLength(): Int

    @Query("select * from books where id = :id")
    fun getBookById(id: Int): Flow<List<Book>>

    @Insert
    suspend fun addBook(book: Book)

    @Delete
    suspend fun deleteBook(book: Book)

    @Update
    suspend fun updateBook(book: Book)
}