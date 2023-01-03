package com.bonial.bronchurelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bonial.domain.base.usecase.UseCaseResult
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.usecases.BrochureListUseCase
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.kotlin.*

internal class BrochureListViewModelTest {

    @get:Rule
    val instant = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutinesTestRule()

    private val brochureListUseCase = mock<BrochureListUseCase>()
    val observerState = mock<Observer<BrochuresListState>>()

    val viewModel by lazy { BrochureListViewModel(brochureListUseCase) }


    @Test
    fun getBrochureListLiveData_success() = runBlocking {
        val response = BrochureListResponse(emptyList())

        whenever(brochureListUseCase.task())
            .thenReturn(UseCaseResult(response))


        whenever(brochureListUseCase.perform(any(), any()))
            .then {

            }

        viewModel.brochureListStateLiveData.observeForever(observerState)

        viewModel.getBrochureList()

        verify(brochureListUseCase).perform()


        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedLoadingState = LoadingState()
        val expectedDefaultState = DefaultState(response.brochureList ?: emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, defaultState) = allValues
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }

//        verify(observerState, times(2)).onChanged(capture())
//        assertEquals(viewModel.brochureListStateLiveData.value, LoadingState())
//        assertEquals(viewModel.brochureListStateLiveData.value, DefaultState(emptyList()))
    }

    @Test
    fun getBrochureListLiveData_error() {
    }

}