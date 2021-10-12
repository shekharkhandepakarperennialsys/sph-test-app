package com.sphtechapp.sphtestapp.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import kotlinx.coroutines.CoroutineScope

class DatastoreDataFactory(private val repository: DatastoreRepository, private val scope: CoroutineScope) : DataSource.Factory<Int, DatastoreRecordEntity>() {

    val liveData = MutableLiveData<DatastoreDataSource>()
    lateinit var datastoreDataSource: DatastoreDataSource

    override fun create(): DataSource<Int, DatastoreRecordEntity> {
        datastoreDataSource = DatastoreDataSource(repository, scope)
        liveData.postValue(datastoreDataSource)
        return datastoreDataSource
    }
}