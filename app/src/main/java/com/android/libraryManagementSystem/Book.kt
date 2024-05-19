package com.android.libraryManagementSystem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.util.Date

@Entity(tableName = "books")
data class Book (
    @PrimaryKey(autoGenerate = true)
    val id : Int=0,
    /* States definitions -> {1: wants to read}, {2: reading}, {3: read} */
    @ColumnInfo(name = "state")
    val state: Int,
    @ColumnInfo(name = "book_name")
    val bookName: String,
    @ColumnInfo(name = "number_of_pages")
    val numberOfPages: Int = 0,
    val description: String?=null,
    )