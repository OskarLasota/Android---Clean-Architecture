package com.example.cleanarchitecture

import com.example.data.repo.home.ProductToModelMapper
import com.example.model.ProductDTO
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArticleDtoToModelMapperTest() {

    @Fixture
    lateinit var fixtItemsDTO: List<ProductDTO>

    private lateinit var sut: ProductToModelMapper


    @Before
    fun setUp() {
        FixtureAnnotations.initFixtures(this)

        sut = ProductToModelMapper()
    }

    @Test
    fun apply() {
        val actualResult = sut.apply(fixtItemsDTO)

        for ((i, value) in fixtItemsDTO.withIndex()) {
            assertEquals(actualResult[i].id, value.id)
            assertEquals(actualResult[i].name, value.name)
            assertEquals(actualResult[i].url, value.url)
        }
    }

}