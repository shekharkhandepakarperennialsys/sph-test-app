package com.sphtechapp.sphtestapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatastoreDao {
    @Query("SELECT * FROM datastore_record")
    suspend fun getDatastoreRecords(): List<DatastoreRecordEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDatastoreRecords(recordDataList: List<DatastoreRecordEntity>)

    @Query("DELETE FROM datastore_record")
    suspend fun deleteDatastoreRecords()

    @Query("SELECT * FROM datastore_record WHERE quarter LIKE '%' || :year")
    suspend fun searchDatastoreRecords(year: String): List<DatastoreRecordEntity>

}