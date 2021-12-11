package com.geekbrains.tests


import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


class DetailsPresenterTest {
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        presenter = DetailsPresenter(viewContract, 0)
    }

    @Test
    fun setCounter_Test(){
        presenter.setCounter(4)
        assertEquals(4, presenter.getCounter())
    }

    @Test
    fun increment_Test(){
        presenter.onIncrement()
        assertEquals(1, presenter.getCounter())
    }

    @Test
    fun decrement_Test(){
        presenter.onDecrement()
        assertEquals(-1, presenter.getCounter())
    }
}