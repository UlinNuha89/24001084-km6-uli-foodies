package com.lynn.foodies.data.datasource.pref

import com.lynn.foodies.data.source.local.pref.UserPreference
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserPrefDataSourceTest {
    @MockK
    lateinit var userPreference: UserPreference

    private lateinit var dataSource: UserDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        dataSource = UserPrefDataSource(userPreference)
    }

    @Test
    fun isUsingGridMode() {
        every { userPreference.isUsingGridMode() } returns false
        val result = dataSource.isUsingGridMode()
        verify { userPreference.isUsingGridMode() }
        assertEquals(false, result)
    }

    @Test
    fun setUsingGridMode() {
        every { userPreference.setUsingGridMode(any()) } returns Unit
        val result = dataSource.setUsingGridMode(true)
        verify { userPreference.setUsingGridMode(any()) }
        assertEquals(Unit, result)
    }
}