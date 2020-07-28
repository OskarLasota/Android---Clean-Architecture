package com.example.cleanarchitecture

import com.example.cleanarchitecture.home.HomeContract
import com.example.cleanarchitecture.home.HomePresenter
import com.example.data.repo.home.ProductToModelMapper
import com.example.domain.caching.GetCachedProductsUseCase
import com.example.domain.home.GetProductUseCase
import com.example.model.ProductDTO
import com.example.model.ProductModel
import com.flextrade.jfixture.FixtureAnnotations
import com.flextrade.jfixture.annotations.Fixture
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomePresenterTest {
    private val scheduler = TestAppScheduler()
    @Mock
    lateinit var mockView: HomeContract.View
    @Mock
    lateinit var mockApiInteractor: GetProductUseCase
    @Mock
    lateinit var mockCacheInteractor: GetCachedProductsUseCase
    @Mock
    lateinit var mockProductDtoToModelMapper: ProductToModelMapper
    @Mock
    lateinit var mockCompositeDisposable: CompositeDisposable

    private lateinit var sut: HomePresenter
    @Fixture
    lateinit var fixtItemsDTO: List<ProductDTO>
    @Fixture
    lateinit var fixtItemsModel: List<ProductModel>




    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        FixtureAnnotations.initFixtures(this)
        sut = HomePresenter(mockView, mockApiInteractor,mockCacheInteractor, mockProductDtoToModelMapper, scheduler, mockCompositeDisposable)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun init_success() {
        //setup
        whenever(mockApiInteractor.getProducts()).thenReturn(Single.just(fixtItemsDTO))
        whenever(mockProductDtoToModelMapper.apply(fixtItemsDTO)).thenReturn(fixtItemsModel)

        //run
        sut.init()

        //verify
        verify(mockApiInteractor).getProducts()
        verify(mockView).initView(fixtItemsModel)
    }


    @Test
    fun init_showDefaultErrorMessage() {
        //setup
        whenever(mockApiInteractor.getProducts()).thenReturn(Single.error(Throwable()))

        //run
        sut.init()

        //verify
        verify(mockView).showError("defaultError")
    }

    @Test
    fun onPause_disposeCalls() {
        //setup
        whenever(mockApiInteractor.getProducts()).thenReturn(Single.just(fixtItemsDTO))
        whenever(mockProductDtoToModelMapper.apply(fixtItemsDTO)).thenReturn(fixtItemsModel)
        sut.init()

        //run
        sut.onPause()

        //verify
        verify(mockCompositeDisposable).clear()
    }


}