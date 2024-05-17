package com.lynn.foodies.data.repository

import android.net.Uri
import app.cash.turbine.test
import com.google.firebase.auth.FirebaseUser
import com.lynn.foodies.data.model.toUser
import com.lynn.foodies.data.source.network.firebase.auth.FirebaseAuthDataSource
import com.lynn.foodies.utils.ResultWrapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class UserRepositoryImplTest {
    @MockK
    lateinit var ds: FirebaseAuthDataSource

    lateinit var repo: UserRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = UserRepositoryImpl(ds)
    }

    @Test
    fun `doLogin success`() {
        coEvery { ds.doLogin(any(), any()) } returns true
        runTest {
            repo.doLogin("aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.doLogin(any(), any()) }
            }
        }
    }

    @Test
    fun `doLogin loading`() {
        coEvery { ds.doLogin(any(), any()) } returns true
        runTest {
            repo.doLogin("aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.doLogin(any(), any()) }
            }
        }
    }

    @Test
    fun `doLogin error`() {
        coEvery { ds.doLogin(any(), any()) } throws IllegalStateException("error")
        runTest {
            repo.doLogin("aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { ds.doLogin(any(), any()) }
            }
        }
    }

    @Test
    fun `doRegister success`() {
        coEvery { ds.doRegister(any(), any(), any()) } returns true
        runTest {
            repo.doRegister("aa", "aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Success)
                coVerify { ds.doRegister(any(), any(), any()) }
            }
        }
    }

    @Test
    fun `doRegister loading`() {
        coEvery { ds.doRegister(any(), any(), any()) } returns true
        runTest {
            repo.doRegister("aa", "aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(110)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Loading)
                coVerify { ds.doRegister(any(), any(), any()) }
            }
        }
    }

    @Test
    fun `doRegister error`() {
        coEvery { ds.doRegister(any(), any(), any()) } throws IllegalStateException("error")
        runTest {
            repo.doRegister("aa", "aa", "aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val data = expectMostRecentItem()
                assertTrue(data is ResultWrapper.Error)
                coVerify { ds.doRegister(any(), any(), any()) }
            }
        }
    }

    @Test
    fun doLogout() {
        every { ds.doLogout() } returns true
        val result = repo.doLogout()
        assertTrue(result)
        verify { ds.doLogout() }
    }

    @Test
    fun isLoggedIn() {
        every { ds.isLoggedIn() } returns true
        val result = repo.isLoggedIn()
        assertTrue(result)
        verify { ds.isLoggedIn() }
    }

    @Test
    fun getCurrentUser() {
        val mockUser = mockk<FirebaseUser>()
        every { mockUser.displayName } returns "adit"
        every { mockUser.email } returns "adit"
        every { mockUser.photoUrl } returns Uri.EMPTY
        every { ds.getCurrentUser() } returns mockUser
        val result = repo.getCurrentUser()
        assertEquals(mockUser.toUser(), result)
        verify { ds.getCurrentUser() }
    }

    @Test
    fun `getCurrentUser null`() {
        every { ds.getCurrentUser() } returns null
        val result = repo.getCurrentUser()
        assertEquals(null, result)
        verify { ds.getCurrentUser() }
    }

    @Test
    fun `updateProfile success`() {
        runTest {
            coEvery { ds.updateProfile(any(), any()) } returns true
            repo.updateProfile().map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Success)
                coVerify { ds.updateProfile(any(), any()) }
            }
        }
    }

    @Test
    fun `updateProfile loading`() {
        runTest {
            coEvery { ds.updateProfile(any(), any()) } returns true
            repo.updateProfile().map {
                delay(100)
                it
            }.test {
                delay(110)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Loading)
                coVerify { ds.updateProfile(any(), any()) }
            }
        }
    }

    @Test
    fun `updateProfile error`() {
        runTest {
            coEvery { ds.updateProfile(any(), any()) } throws IllegalStateException("error")
            repo.updateProfile().map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Error)
                coVerify { ds.updateProfile(any(), any()) }
            }
        }
    }

    @Test
    fun `updatePassword success`() {
        runTest {
            coEvery { ds.updatePassword(any()) } returns true
            repo.updatePassword("aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Success)
                coVerify { ds.updatePassword(any()) }
            }
        }
    }

    @Test
    fun `updatePassword loading`() {
        runTest {
            coEvery { ds.updatePassword(any()) } returns true
            repo.updatePassword("aa").map {
                delay(100)
                it
            }.test {
                delay(110)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Loading)
                coVerify { ds.updatePassword(any()) }
            }
        }
    }

    @Test
    fun `updatePassword error`() {
        runTest {
            coEvery { ds.updatePassword(any()) } throws IllegalStateException("error")
            repo.updatePassword("aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Error)
                coVerify { ds.updatePassword(any()) }
            }
        }
    }

    @Test
    fun `updateEmail success`() {
        runTest {
            coEvery { ds.updateEmail(any()) } returns true
            repo.updateEmail("aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Success)
                coVerify { ds.updateEmail(any()) }
            }
        }
    }

    @Test
    fun `updateEmail Loading`() {
        runTest {
            coEvery { ds.updateEmail(any()) } returns true
            repo.updateEmail("aa").map {
                delay(100)
                it
            }.test {
                delay(110)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Loading)
                coVerify { ds.updateEmail(any()) }
            }
        }
    }

    @Test
    fun `updateEmail error`() {
        runTest {
            coEvery { ds.updateEmail(any()) } throws IllegalStateException("error")
            repo.updateEmail("aa").map {
                delay(100)
                it
            }.test {
                delay(210)
                val result = expectMostRecentItem()
                assertTrue(result is ResultWrapper.Error)
                coVerify { ds.updateEmail(any()) }
            }
        }
    }

    @Test
    fun sendChangePasswordRequestByEmail() {
        every { ds.sendChangePasswordRequestByEmail() } returns true
        assertTrue(repo.sendChangePasswordRequestByEmail())
        verify { ds.sendChangePasswordRequestByEmail() }
    }
}