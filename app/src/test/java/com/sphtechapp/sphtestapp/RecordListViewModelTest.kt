package com.sphtechapp.sphtestapp

import androidx.lifecycle.Observer
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.di.apiModule
import com.sphtechapp.sphtestapp.di.databaseModule
import com.sphtechapp.sphtestapp.di.datastoreModule
import com.sphtechapp.sphtestapp.di.retrofitModule
import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import com.sphtechapp.sphtestapp.view.RecordListViewModel
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecordListViewModelTest {
//    @Mock
//    private lateinit var repository: DatastoreRepository
//
//    private lateinit var recordListViewModel: RecordListViewModel
//
//    @Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var datastoreRecordEntity: DatastoreRecordEntity
//
//    @Mock
//    private lateinit var observer: Observer<List<DatastoreRecordEntity>>


//    @Before
//    fun onSetup() {
//        MockitoAnnotations.initMocks(this)
//        recordListViewModel = RecordListViewModel(repository)
//    }

//    @Test
//    fun fetchUser_ShouldReturnUser() {
//      datastoreRecordEntity= DatastoreRecordEntity(1,"2004","1.45345")
//
//        recordListViewModel.getRecords().observeForever{}
//
////        val captor = ArgumentCaptor.forClass(DatastoreRecordEntity::class.java)
////        captor.run {
////            verify(observer, times(1)).onChanged(listOf(capture()))
////            assertEquals(recordListViewModel.searchRecords(), value)
////        }
//
//
//    }


//    val viewModel: RecordListViewModel by inject()
//    val repository: DatastoreRepository by inject()
//
//    @Mock
//    private lateinit var observer: Observer<List<DatastoreRecordEntity>>
//
//    @Before
//    fun before() {
//        MockitoAnnotations.initMocks(this)
//        startKoin {
//            androidFileProperties()
//            modules( listOf(retrofitModule, apiModule, databaseModule, datastoreModule))
//        }
//    }



}