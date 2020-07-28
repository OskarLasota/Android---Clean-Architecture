package com.example.data

import com.example.data.repo.caching.ProductCache
import com.example.data.repo.home.ProductRepository
import com.example.data.repo.home.service.ProductService
import com.example.model.ProductDTO
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Single
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    lateinit var service : ProductService

    lateinit var sut : ProductRepository
    @Fixture
    lateinit var fixtItemsDTO: List<ProductDTO>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this)
        sut = ProductRepository(service)
    }

    @Test
    fun `getProducts() called in service`() {
        whenever(service.getProducts()).thenReturn(Single.just(fixtItemsDTO))
        val result = sut.getProducts()
        println(fixtItemsDTO)
        println("here")
        println(result)
        assertTrue(fixtItemsDTO == result)
    }



}