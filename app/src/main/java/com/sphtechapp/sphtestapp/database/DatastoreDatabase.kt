package com.sphtechapp.sphtestapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatastoreRecordEntity::class], version = 1, exportSchema = true)
abstract class DatastoreDatabase : RoomDatabase() {
    abstract fun datastoreDao(): DatastoreDao

    companion object {
        @Volatile
        private var instance: DatastoreDatabase? = null

        fun getInstance(context: Context): DatastoreDatabase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DatastoreDatabase::class.java,
                        "datastore-records.db"
                    ).build()
                }
            }
            return instance!!
        }
    }
}