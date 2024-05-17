package com.lynn.foodies.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.catnip.kokomputer.tools.MainCoroutineRule
import com.catnip.kokomputer.tools.getOrAwaitValue
import com.lynn.foodies.data.repository.CatalogRepository
import com.lynn.foodies.data.repository.CategoryRepository
import com.lynn.foodies.data.repository.UserRepository
import com.lynn.foodies.data.source.local.pref.UserPreference
import com.lynn.foodies.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeViewModelTest {
    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule: TestRule = MainCoroutineRule(UnconfinedTestDispatcher())

    @MockK
    private lateinit var categoryRepository: CategoryRepository

    @MockK
    private lateinit var catalogRepository: CatalogRepository

    @MockK
    private lateinit var userPreference: UserPreference

    @MockK
    private lateinit var userRepository: UserRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { userRepository.isLoggedIn() } returns true
        every { userPreference.isUsingGridMode() } returns true
        viewModel = spyk(
            HomeViewModel(
                categoryRepository,
                catalogRepository,
                userPreference,
                userRepository
            )
        )
    }

    @Test
    fun isLoggedIn() {
        val result = viewModel.isLoggedIn
        assertTrue(result)
        verify { userRepository.isLoggedIn() }
    }

    @Test
    fun getUsername() {
        every { userRepository.getCurrentUser()?.fullName } returns "andi"
        val result = viewModel.getUsername()
        assertEquals("andi", result)
        verify { userRepository.getCurrentUser() }
    }

    @Test
    fun getCatalogName() {
        every { viewModel.catalogName } returns "burger"
        val result = viewModel.catalogName
        assertEquals("burger", result)
        verify { viewModel.catalogName }
    }

    @Test
    fun setCatalogName() {
        viewModel.catalogName = "burger"
        assertEquals("burger", viewModel.catalogName)
        verify { viewModel.catalogName }
    }

    @Test
    fun setPref() {
        every { userPreference.setUsingGridMode(any()) } returns Unit
        viewModel.setPref(false)
        verify { userPreference.setUsingGridMode(any()) }
    }

    @Test
    fun getCategories() {
        every { categoryRepository.getCategories() } returns flow {
            emit(ResultWrapper.Success(listOf(mockk(), mockk())))
        }
        val result = viewModel.getCategories().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { categoryRepository.getCategories() }
    }

    @Test
    fun getCatalogs() {
        every { catalogRepository.getCatalogs(any()) } returns flow {
            emit(ResultWrapper.Success(listOf(mockk(), mockk())))
        }
        val result = viewModel.getCatalogs().getOrAwaitValue()
        assertEquals(2, result.payload?.size)
        verify { catalogRepository.getCatalogs() }
    }

    @Test
    fun isUsingGridMode() {
        every { userPreference.isUsingGridMode() } returns true
        val result = viewModel.isUsingGridMode.value
        assertEquals(true, result)
        verify { userPreference.isUsingGridMode() }
    }

    @Test
    fun changeListMode() {
        every { userPreference.isUsingGridMode() } returns true
        viewModel.changeListMode()
        val result = viewModel.isUsingGridMode.value
        assertEquals(false, result)
    }
}