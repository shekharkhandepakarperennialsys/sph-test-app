package com.sphtechapp.sphtestapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sphtechapp.sphtestapp.BuildConfig
import com.sphtechapp.sphtestapp.database.DatastoreDatabase
import com.sphtechapp.sphtestapp.network.ApiInterceptor
import com.sphtechapp.sphtestapp.network.NetworkReachabilityInterceptor
import com.sphtechapp.sphtestapp.network.NetworkStateChecker
import com.sphtechapp.sphtestapp.network.NetworkStateCheckerImpl
import com.sphtechapp.sphtestapp.network.service.DatastoreApiService
import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import com.sphtechapp.sphtestapp.view.DatastorePagerViewModel
import com.sphtechapp.sphtestapp.view.RecordListViewModel
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun provideGitHubApi(retrofit: Retrofit) = retrofit.create(DatastoreApiService::class.java)
    factory { provideGitHubApi(retrofit = get()) }
}

val databaseModule = module {
    single { DatastoreDatabase.getInstance(get()) }
    single { get<DatastoreDatabase>().datastoreDao() }
}

val retrofitModule = module {
    fun provideOkHttpClient(networkStateChecker: NetworkStateChecker): OkHttpClient {
        val timeoutInSeconds = 120
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        val builder = OkHttpClient.Builder()
            .connectTimeout(timeoutInSeconds.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeoutInSeconds.toLong(), TimeUnit.SECONDS)
            .dispatcher(dispatcher)
            .addInterceptor(NetworkReachabilityInterceptor(networkStateChecker))
            .addInterceptor(ApiInterceptor())

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(interceptor)
        return builder.build()
    }

    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    factory { provideGson() }
    factory { NetworkStateCheckerImpl(get()) as NetworkStateChecker }
    factory { provideOkHttpClient(get()) }
    factory { provideRetrofit(get(), get()) }
}

val datastoreModule = module {
    factory { DatastoreRepository(get(), get()) }
    viewModel {
        RecordListViewModel(get())
    }
    viewModel {
        DatastorePagerViewModel(get())
    }
}

