package life.league.challenge.kotlin.data.sharedpreferences

import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


@RunWith(MockitoJUnitRunner::class)
class SettingRepositoryImplTest {

    @Mock
    private lateinit var sharedPref: SharedPref


    private lateinit var settingRepository: SettingRepository


    @Before
    fun setup() {
        settingRepository = SettingRepositoryImpl(sharedPref)
    }

    @Test
    fun `hasToken() call read() with correct arguments`() {
        settingRepository.hasToken()
        verify(sharedPref).read(eq(KEY_TOKEN), eq(null))
    }

    @Test
    fun `hasToken() return false when Token not saved in sharedPref`() {
        whenever(sharedPref.read(KEY_TOKEN, null)).thenReturn(null)
        val expected = settingRepository.hasToken()
        Assert.assertFalse(expected)
    }

    @Test
    fun `hasToken() return true when Token saved in sharedPref`() {
        val token = "doesn't Important"
        whenever(sharedPref.read(KEY_TOKEN, null)).thenReturn(token)
        val expected = settingRepository.hasToken()
        Assert.assertTrue(expected)
    }

    @Test
    fun `saveToken() call write() with correct arguments`() {
        val token = "doesn't Important"
        settingRepository.saveToken(token)
        verify(sharedPref).write(eq(KEY_TOKEN), eq(token.toString()))
    }

    @Test
    fun `getToken() call read() with correct arguments`() {
        settingRepository.getToken()
        verify(sharedPref).read(eq(KEY_TOKEN), eq(null))
    }

    @Test
    fun `getToken() return null when read return null`() {
        whenever(sharedPref.read(KEY_TOKEN, null)).thenReturn(null)
        val expected = settingRepository.getToken()
        Assert.assertNull(expected)
    }

    @Test
    fun `getToken() return Token when read return not null`() {
        val token = "doesn't Important"
        whenever(sharedPref.read(KEY_TOKEN, null)).thenReturn(token)
        val actual = settingRepository.getToken()
        assertEquals(token, actual.toString())
    }

}

