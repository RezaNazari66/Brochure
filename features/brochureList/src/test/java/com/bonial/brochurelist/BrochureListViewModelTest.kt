package com.bonial.brochurelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.bonial.domain.models.Brochure
import com.bonial.domain.models.BrochureListResponse
import com.bonial.domain.usecases.BrochureListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
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
    private val observerState = mock<Observer<BrochuresListState>>()

    private val viewModel by lazy { BrochureListViewModel(brochureListUseCase) }

    @Before
    fun initTest() {
        reset(brochureListUseCase, observerState)
    }

    @Test
    fun getBrochureListLiveData_returnSuccessState() = runBlocking {
        //GIVEN
        val response = BrochureListResponse(emptyList())

        whenever(brochureListUseCase.executeAsync())
            .thenReturn(response)

        viewModel.brochureListStateLiveData.observeForever(observerState)


        //WHEN
        viewModel.getBrochureList()

        //THEN
        verify(brochureListUseCase).executeAsync()

        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedLoadingState = LoadingState()
        val expectedDefaultState = DefaultState(response.brochureList ?: emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, defaultState) = allValues
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun getBrochureListLiveData_returnErrorState() = runBlocking {

        whenever(brochureListUseCase.executeAsync())
            .then {
                throw java.lang.Exception()
            }

        viewModel.brochureListStateLiveData.observeForever(observerState)

        viewModel.getBrochureList()

        verify(brochureListUseCase).executeAsync()


        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedLoadingState = LoadingState()
        val expectedErrorState = ErrorState("")

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, errorState) = allValues
            assertEquals(loadingState, expectedLoadingState)
            assertEquals(errorState, expectedErrorState)
        }
    }

    @Test
    fun getBrochureListLiveData_withoutBrochuresOrPremium_returnEmptyList() = runBlocking {

        //GIVEN
        val response = BrochureListResponse(
            listOf(
                MockedBrochures.otherBrochure,
                MockedBrochures.otherBrochure,
                MockedBrochures.otherBrochure,
                MockedBrochures.otherBrochure
            )
        )

        whenever(brochureListUseCase.executeAsync())
            .thenReturn(response)

        viewModel.brochureListStateLiveData.observeForever(observerState)

        //WHEN
        viewModel.getBrochureList()


        //THEN
        verify(brochureListUseCase).executeAsync()
        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val defaultState = allValues[1]
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun getBrochureListLiveData_allBrochuresAndPremium_returnAllItems() = runBlocking {
        //GIVEN

        val response = BrochureListResponse(
            listOf(
                MockedBrochures.brochure,
                MockedBrochures.brochure,
                MockedBrochures.brochure,
                MockedBrochures.premiumBrochure
            )
        )

        whenever(brochureListUseCase.executeAsync())
            .thenReturn(response)

        viewModel.brochureListStateLiveData.observeForever(observerState)

        //WHEN
        viewModel.getBrochureList()

        //THEN
        verify(brochureListUseCase).executeAsync()

        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(response.brochureList.orEmpty())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val defaultState = allValues[1]
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun getBrochureListLiveData_twoBrochuresTwoOther_returnTwoBrochures() = runBlocking {
        //GIVEN
        val response = BrochureListResponse(
            listOf(
                MockedBrochures.brochure,
                MockedBrochures.brochure,
                MockedBrochures.otherBrochure,
                MockedBrochures.otherBrochure
            )
        )

        whenever(brochureListUseCase.executeAsync())
            .thenReturn(response)

        viewModel.brochureListStateLiveData.observeForever(observerState)

        //WHEN
        viewModel.getBrochureList()


        //THEN
        verify(brochureListUseCase).executeAsync()

        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState =
            DefaultState(listOf(MockedBrochures.brochure, MockedBrochures.brochure))

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val defaultState = allValues[1]
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun getBrochureListLiveData_null_returnEmptyList() = runBlocking {
        //GIVEN
        val response = BrochureListResponse(null)

        whenever(brochureListUseCase.executeAsync())
            .thenReturn(response)

        viewModel.brochureListStateLiveData.observeForever(observerState)

        //WHEN
        viewModel.getBrochureList()

        //THEN
        verify(brochureListUseCase).executeAsync()

        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val defaultState = allValues[1]
            assertEquals(defaultState, expectedDefaultState)
        }
    }

    @Test
    fun toggleFilterItem_emptyList_returnEmptyList() = runBlocking {
        //GIVEN
        val list = emptyList<Brochure>()
        viewModel.brochureListStateLiveData.observeForever(observerState)
        viewModel.setBrochureListStateLiveData(list)

        //WHEN
        viewModel.toggleFilterItem()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())

            assertEquals(allValues[1], expectedDefaultState)
        }
    }

    @Test
    fun toggleFilterItem_allItemsAreNear_returnAll() = runBlocking {
        //GIVEN
        val list =
            listOf(
                MockedBrochures.brochure,
                MockedBrochures.brochure,
                MockedBrochures.brochure,
            )

        viewModel.brochureListStateLiveData.observeForever(observerState)
        viewModel.setBrochureListStateLiveData(list)

        //WHEN
        viewModel.toggleFilterItem()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(list)

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            assertEquals(allValues[1], expectedDefaultState)
        }
    }

    @Test
    fun toggleFilterItem_twoItemNear_returnTwoItem() = runBlocking {
        //GIVEN
        val list =
            listOf(
                MockedBrochures.brochure,
                MockedBrochures.brochure,
                MockedBrochures.brochureLongDistance,
                MockedBrochures.brochureLongDistance
            )

        viewModel.brochureListStateLiveData.observeForever(observerState)
        viewModel.setBrochureListStateLiveData(list)

        //WHEN
        viewModel.toggleFilterItem()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(
            listOf(
                MockedBrochures.brochure,
                MockedBrochures.brochure,
            )
        )

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            assertEquals(allValues[1], expectedDefaultState)
        }
    }

    @Test
    fun toggleFilterItem_NoItemNear_returnEmptyList() = runBlocking {
        //GIVEN
        val list =
            listOf(
                MockedBrochures.brochureLongDistance,
                MockedBrochures.brochureLongDistance,
                MockedBrochures.brochureLongDistance
            )

        viewModel.brochureListStateLiveData.observeForever(observerState)
        viewModel.setBrochureListStateLiveData(list)

        //WHEN
        viewModel.toggleFilterItem()

        //THEN
        val argumentCaptor = ArgumentCaptor.forClass(BrochuresListState::class.java)
        val expectedDefaultState = DefaultState(emptyList())

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            assertEquals(allValues[1], expectedDefaultState)
        }
    }


}