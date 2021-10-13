package com.sphtechapp.sphtestapp.repository

import com.sphtechapp.sphtestapp.common.DataResult
import com.sphtechapp.sphtestapp.database.DatastoreDao
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.network.ErrorHandler
import com.sphtechapp.sphtestapp.network.service.DatastoreApiService
import com.sphtechapp.sphtestapp.view.DatastoreContract

class DatastoreRepository(private val datastoreApiService: DatastoreApiService, private val datastoreDao: DatastoreDao) : DatastoreContract.Repository {

    override suspend fun searchDatastore(): DataResult<List<DatastoreRecordEntity>> {
        val storedRecords = datastoreDao.getDatastoreRecords()
        if (storedRecords.isNotEmpty()) {
            return DataResult.DataSuccess(storedRecords)
        }

        val response = datastoreApiService.searchDatastore("a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
        return if (response.isSuccessful) {
            val responseRecords = response.body()!!.result.records
            val mappedResponse = responseRecords.map { DatastoreRecordEntity(it._id, it.quarter, it.volume_of_mobile_data) }
            datastoreDao.insertDatastoreRecords(mappedResponse)
            DataResult.DataSuccess(mappedResponse)
        } else {
            DataResult.DataError(ErrorHandler.getError(response))
        }
    }

    override suspend fun searchDatastoreEntry(year: String): DataResult<List<DatastoreRecordEntity>> {
        val storedRecords = datastoreDao.searchDatastoreRecords(year)
        return if (storedRecords.isNotEmpty()) {
            DataResult.DataSuccess(storedRecords)
        } else {
            DataResult.DataError("No data")
        }
    }
}