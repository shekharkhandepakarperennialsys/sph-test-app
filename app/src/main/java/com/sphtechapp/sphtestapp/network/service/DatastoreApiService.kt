package com.sphtechapp.sphtestapp.network.service

import com.sphtechapp.sphtestapp.model.DatastoreResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Perennial Dev on 6/10/21
 */

interface DatastoreApiService {

    @GET("api/action/datastore_search")
    suspend fun searchDatastore(@Query("resource_id") resourceId: String): Response<DatastoreResponse>
}