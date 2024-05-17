package com.lynn.foodies.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.UserRepository
import com.lynn.foodies.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class LoginViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var repo: UserRepository

    private lateinit var viewModel : LoginViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(LoginViewModel(repo))
    }
/*
    @Test
    fun getLoginResult() {
        val observer = mockk<Observer<ResultWrapper<Boolean>>>(relaxed = true)
        every { viewModel.loginResult.value } returns ResultWrapper.Success(true)
        viewModel.doLogin("","")
        verify { observer.onChanged(ResultWrapper.Success(true)) }
        viewModel.loginResult.removeObserver(observer)
    }*/

    @Test
    fun `doLogin success`() {
        coEvery { repo.doLogin(any(),any()) } returns flow {
            emit(ResultWrapper.Success(true))
        }
        viewModel.doLogin("as","ads")
        coVerify { repo.doLogin(any(),any()) }

    }
}