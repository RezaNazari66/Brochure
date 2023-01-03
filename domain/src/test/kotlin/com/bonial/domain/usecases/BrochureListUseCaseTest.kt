package com.bonial.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


//todo complete it
//class BrochureListUseCaseTest {
//
//    @Rule
//    @JvmField
//    val rule = InstantTaskExecutorRule()
//
//    private val brochuresRepository: BrochuresRepository = mock(BrochuresRepository::class.java)
//
//    val brochureListUseCas by lazy { BrochureListUseCase(brochuresRepository) }
//
//
//
//    @Test
//    fun testCryptoListUseCases_getCryptoList_Completed() {
//        runBlocking {
//
//            whenever(brochureListUseCas.perform{})
//                .thenReturn()
//        }
//
//
//        cryptoListUseCases.getCryptoListBy(0)
//            .test()
//            .assertComplete()
//    }
//
//    @Test
//    fun testCryptoListUseCases_getCryptoList_Error() {
//        val response = Throwable("Error response")
//        whenever(coinMarketCapRepository.getCryptoList(anyInt(), anyInt()))
//            .thenReturn(Single.error(response))
//
//        cryptoListUseCases.getCryptoListBy(0)
//            .test()
//            .assertError(response)
//
//    }
//
//    @Test
//    fun testCryptoListUseCases_getCryptoList_response() {
//        val response = arrayListOf(cryptoPOJOmodel())
//        whenever(coinMarketCapRepository.getCryptoList(anyInt(), anyInt()))
//            .thenReturn(Single.just(response))
//
//        val expectedList = arrayListOf(cryptoViewModelFrom(cryptoPOJOmodel()))
//
//        cryptoListUseCases.getCryptoListBy(0)
//            .test()
//            .assertValue(expectedList)
//    }
//}