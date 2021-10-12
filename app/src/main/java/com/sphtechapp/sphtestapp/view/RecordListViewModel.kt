package com.sphtechapp.sphtestapp.view

import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sphtechapp.sphtestapp.common.DataResult
import com.sphtechapp.sphtestapp.common.ProgressStatus
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.repository.DatastoreDataFactory
import com.sphtechapp.sphtestapp.repository.DatastoreDataSource
import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import kotlinx.coroutines.launch

class RecordListViewModel(private val repository: DatastoreRepository) : ViewModel(), DatastoreContract.ViewModel {
    private val recordDataSourceFactory: DatastoreDataFactory = DatastoreDataFactory(repository, viewModelScope)
    private val progressLoadStatus: MutableLiveData<ProgressStatus> = MutableLiveData()

    private val dataList: MutableLiveData<List<DatastoreRecordEntity>> = MutableLiveData()

    override fun getRecords() = dataList

    override fun getProgressStatus() = progressLoadStatus

    override fun searchRecords() {
        viewModelScope.launch {
            progressLoadStatus.postValue(ProgressStatus.Loading)
            when (val dataResult = repository.searchDatastore()) {
                is DataResult.DataSuccess -> {
                    progressLoadStatus.postValue(ProgressStatus.Success)
                    dataList.postValue(dataResult.data)
                }
                is DataResult.DataError -> progressLoadStatus.postValue(ProgressStatus.Error(dataResult.errorMessage))
            }
        }
    }
}