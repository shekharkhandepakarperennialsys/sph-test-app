package com.sphtechapp.sphtestapp.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.sphtechapp.sphtestapp.common.DataResult
import com.sphtechapp.sphtestapp.common.ProgressStatus
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DatastoreDataSource(private val repository: DatastoreRepository, private val scope: CoroutineScope) : PageKeyedDataSource<Int, DatastoreRecordEntity>() {
    private val progressLiveStatus = MutableLiveData<ProgressStatus>()

    fun getProgressLiveStatus() = progressLiveStatus

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, DatastoreRecordEntity>) {
        scope.launch {
            progressLiveStatus.postValue(ProgressStatus.Loading)
            when (val dataResult = repository.searchDatastore()) {
                is DataResult.DataSuccess -> {
                    progressLiveStatus.postValue(ProgressStatus.Success)
                    callback.onResult(dataResult.data, null, 2)
                }
                is DataResult.DataError -> progressLiveStatus.postValue(ProgressStatus.Error(dataResult.errorMessage))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, DatastoreRecordEntity>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, DatastoreRecordEntity>) {
    }
}