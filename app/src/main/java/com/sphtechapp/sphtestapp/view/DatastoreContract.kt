package com.sphtechapp.sphtestapp.view

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sphtechapp.sphtestapp.common.DataResult
import com.sphtechapp.sphtestapp.common.ProgressStatus
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity

interface DatastoreContract {
    interface ViewModel {
        fun getRecords(): LiveData<List<DatastoreRecordEntity>>
        fun getProgressStatus(): LiveData<ProgressStatus>
        fun searchRecords()
    }

    interface Repository {
        suspend fun searchDatastore(): DataResult<List<DatastoreRecordEntity>>
        suspend fun searchDatastoreEntry(year: String): DataResult<List<DatastoreRecordEntity>>
    }

    interface ViewPagerViewModel {
        fun searchDatastoreEntry(year: String)
        fun getDatastoreEntry() : LiveData<List<DatastoreRecordEntity>>
    }
}