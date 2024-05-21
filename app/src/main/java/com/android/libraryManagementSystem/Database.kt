package com.android.libraryManagementSystem

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class]
    , version = 1)
abstract class Database: RoomDatabase() {
    abstract fun bookDao(): BookDao
}