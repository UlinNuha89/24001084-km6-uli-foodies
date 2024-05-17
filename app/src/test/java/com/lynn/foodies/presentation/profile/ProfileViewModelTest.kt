package com.lynn.foodies.presentation.profile

import android.net.Uri
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import com.lynn.foodies.data.model.User
import com.lynn.foodies.data.repository.CartRepository
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
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class ProfileViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var repo: UserRepository

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = spyk(ProfileViewModel(repo))
    }

    @Test
    fun getChangePhotoResult() {
        every { viewModel.changePhotoResult } returns liveData {
            emit(ResultWrapper.Success(true))
        }
        val result = viewModel.changePhotoResult.getOrAwaitValue()
        assertTrue(result is ResultWrapper.Success)
        assertEquals(true, result.payload)
        verify { viewModel.changePhotoResult }
    }

    @Test
    fun getChangeProfileResult() {
        every { viewModel.changeProfileResult } returns liveData {
            emit(ResultWrapper.Success(true))
        }
        val result = viewModel.changeProfileResult.getOrAwaitValue()
        assertTrue(result is ResultWrapper.Success)
        assertEquals(true, result.payload)
        verify { viewModel.changeProfileResult }
    }

    @Test
    fun getCurrentUser() {
        val mockUser = mockk<User>()
        every { repo.getCurrentUser() } returns mockUser
        viewModel.getCurrentUser()
        verify { repo.getCurrentUser() }
    }

    @Test
    fun updateFullName() {
        coEvery { repo.updateProfile(any(), any()) } returns flow {
            emit(ResultWrapper.Success(true))
        }
        viewModel.updateFullName("aa")
        coVerify { repo.updateProfile(any(), any()) }

    }

    @Test
    fun doLogout() {
        every { repo.doLogout() } returns true
        viewModel.doLogout()
        verify { repo.doLogout() }
    }

    @Test
    fun createChangePwdRequest() {
        every { repo.sendChangePasswordRequestByEmail() } returns true
        viewModel.createChangePwdRequest()
        verify { repo.sendChangePasswordRequestByEmail() }
    }

    @Test
    fun updateProfilePicture() {
        coEvery { repo.updateProfile(any(), any()) } returns flow {
            emit(ResultWrapper.Success(true))
        }
        viewModel.updateProfilePicture(mockk<Uri>(relaxed = true))
        coVerify { repo.updateProfile(any(), any()) }

    }
}