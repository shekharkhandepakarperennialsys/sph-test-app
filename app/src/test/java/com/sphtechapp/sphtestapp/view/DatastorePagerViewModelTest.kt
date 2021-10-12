package com.sphtechapp.sphtestapp.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sphtechapp.sphtestapp.database.DatastoreRecordEntity
import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.doNothing
import org.mockito.Mockito.doReturn
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`




@RunWith(MockitoJUnitRunner::class)
class DatastorePagerViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: DatastorePagerViewModel

    @Mock
    lateinit var repository: DatastoreRepository


    lateinit var listObserver: MutableLiveData<List<DatastoreRecordEntity>>


    @Before
    fun setUp(){
      //viewModel=DatastorePagerViewModel(repository)
        listObserver.value= listOf(DatastoreRecordEntity(1,"2004","0.546"))
    }

//    @Test
//    fun searchDatastoreEntry() {
//        viewModel.searchDatastoreEntry("2005")
//
//        viewModel.getDatastoreEntry().observeForever(listObserver)
//
//
//        val value = viewModel.getDatastoreEntry().value ?: error("No value")
//
//        Mockito.verify(listObserver).onChanged(value)
//    }

    @Test
    fun searchDataStoreEntry(){
        doNothing().`when`(viewModel).searchDatastoreEntry("2004")
    }
    @Test
    fun testDataListMutableLiveData(){
        `when`(viewModel.getDatastoreEntry()).thenReturn(listObserver)
    }
}