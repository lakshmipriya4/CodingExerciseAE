package com.example.codingexerciseae.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.codingexerciseae.model.Hiring
import com.example.codingexerciseae.repository.HiringRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class HiringListViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var hiringListRepository: HiringRepository
    private lateinit var mockHiringViewModel: HiringViewModel

    private val mockHiringList = listOf(
        Hiring(id = 1, listId = 1, name = "Item1"),
        Hiring(id = 2, listId = 1, name = "Item12"),
        Hiring(id = 3, listId = 2, name = "Item11")
    )

    @Before
    fun setup() {
        hiringListRepository = mockk()
        mockHiringViewModel = HiringViewModel(hiringListRepository)
    }

    @Test
    fun test__on_successful_response__and_filters_blank_names() = runTest {
        val response = mockk<Response<List<Hiring>>>()
        coEvery { response.body() } returns mockHiringList

        coEvery { hiringListRepository.getHiringList() } returns response

        val observer = mockk<Observer<List<Hiring>>>(relaxed = true)
        mockHiringViewModel.hiringList.observeForever(observer)

        mockHiringViewModel.fetchHiringList()

        verify { observer.onChanged(mockHiringList) }
    }

    @Test
    fun test_on_error_response_calls_errorMessage() = runTest {
        val response = mockk<Response<List<Hiring>>>()
        coEvery { response.body() } returns null
        coEvery { hiringListRepository.getHiringList() } returns response
        val observer = mockk<Observer<String>>(relaxed = true)

        mockHiringViewModel.fetchHiringList()

        mockHiringViewModel.errorMessage.observeForever(observer)

    }
}