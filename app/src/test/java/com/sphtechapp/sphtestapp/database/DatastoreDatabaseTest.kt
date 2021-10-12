package com.sphtechapp.sphtestapp.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatastoreDatabaseTest:TestCase() {

    private lateinit var db: DatastoreDatabase
    private lateinit var dao: DatastoreDao

    @Before
    override fun setUp() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DatastoreDatabase::class.java).build()
        dao = db.datastoreDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadDataStore() = runBlocking<Unit> {
        val data = DatastoreRecordEntity(1, "2005","0.5432")
        dao.insertDatastoreRecords(listOf(data))
        val allDataStore = dao.getDatastoreRecords()
        assertThat(allDataStore.containsAll(listOf(data))).isTrue()
    }


}