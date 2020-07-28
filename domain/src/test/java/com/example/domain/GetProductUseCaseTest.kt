package com.example.domain

import com.example.data.repo.home.ProductRepository
import com.example.domain.home.GetProductUseCaseImpl
import com.example.model.ProductDTO
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.nhaarman.mockito_kotlin.atLeastOnce
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GetProductUseCaseTest {


    @Mock
    lateinit var repo : ProductRepository

    lateinit var sut : GetProductUseCaseImpl

    @Fixture
    lateinit var fixtItemsDTO: List<ProductDTO>


    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this)
        sut = GetProductUseCaseImpl(repo)
    }

    @Test
    fun `test getProducts()`(){
        whenever(repo.getProducts()).thenReturn(Single.just(fixtItemsDTO))

        sut.getProducts()

        verify(repo, atLeastOnce()).getProducts()
    }

    @Test
    fun `coroutine test getCoroutineProducts()`(){
        runBlocking {
            whenever(repo.getCoroutinesProducts()).thenReturn(fixtItemsDTO)
            val result = sut.getCoroutineProducts()
            verify(repo, atLeastOnce()).getCoroutinesProducts()
            assertTrue(fixtItemsDTO == result)
        }

    }



}