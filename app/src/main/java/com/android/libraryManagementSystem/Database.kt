package com.android.libraryManagementSystem

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Book::class]
    , version = 1)
abstract class Database: RoomDatabase() {
    abstract fun bookDao(): BookDao

    /*
    companion object {
        @Volatile private var instance: PeopleDatabase? = null //for singleton

        fun getInstance(context: Context) : PeopleDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): PeopleDatabase {
            return androidx.room.Room.databaseBuilder(context, PeopleDatabase::class.java, "familiar.db").allowMainThreadQueries().build()
        }
    }*/

}

/*
@Database(entities = [Person::class],version=1)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao


    companion object {
        @Volatile
        private var Instance: PeopleDatabase? = null

        fun getDatabase(context: Context): PeopleDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, PeopleDatabase::class.java, "people_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
*/