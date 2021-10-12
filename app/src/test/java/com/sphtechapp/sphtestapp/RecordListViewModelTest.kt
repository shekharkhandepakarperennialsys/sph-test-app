package com.sphtechapp.sphtestapp

import com.sphtechapp.sphtestapp.repository.DatastoreRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecordListViewModelTest {
    @Mock
    private lateinit var repository: DatastoreRepository

    @Before
    fun onSetup() {
        MockitoAnnotations.initMocks(this)

    }
}