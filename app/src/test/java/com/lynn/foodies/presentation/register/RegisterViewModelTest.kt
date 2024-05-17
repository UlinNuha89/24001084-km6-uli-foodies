package com.lynn.foodies.presentation.register

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import com.lynn.foodies.data.repository.UserRepository
import com.lynn.foodies.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
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

class RegisterViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var repo: UserRepository

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(RegisterViewModel(repo))
    }

    @Test
    fun getRegisterResult() {
        every { viewModel.registerResult } returns liveData {
            emit(ResultWrapper.Success(true))
        }
        val result = viewModel.registerResult.getOrAwaitValue()
        assertTrue(result is ResultWrapper.Success)
        assertEquals(true, result.payload)
        verify { viewModel.registerResult }
    }

    @Test
    fun doRegister() {
        coEvery { repo.doRegister(any(),any(),any()) } returns flow {
            emit(ResultWrapper.Success(true))
        }
        viewModel.doRegister("a","a","a")
        coVerify { repo.doRegister(any(),any(),any()) }
    }
}