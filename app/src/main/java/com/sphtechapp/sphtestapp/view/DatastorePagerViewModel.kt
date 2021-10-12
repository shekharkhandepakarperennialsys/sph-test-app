package com.sphtechapp.sphtestapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sphtechapp.sphtestapp.common.DataResult
import com.sphtechapp.sphtestapp.common.ProgressStatus
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import kotlinx.coroutines.launch

class DatastorePagerViewModel(private val repository: DatastoreRepository) : ViewModel(), DatastoreContract.ViewPagerViewModel {
    private var progressLoadStatus: MutableLiveData<ProgressStatus> = MutableLiveData()
     var dataList: MutableLiveData<List<DatastoreRecordEntity>> = MutableLiveData()

    override fun searchDatastoreEntry(year: String) {
        viewModelScope.launch {
            progressLoadStatus.postValue(ProgressStatus.Loading)
            when (val dataResult = repository.searchDatastoreEntry(year)) {
                is DataResult.DataSuccess -> {
                    progressLoadStatus.postValue(ProgressStatus.Success)
                    dataList.postValue(dataResult.data)
                }
                is DataResult.DataError -> progressLoadStatus.postValue(ProgressStatus.Error(dataResult.errorMessage))
            }
        }
    }

    override fun getDatastoreEntry() = dataList
}