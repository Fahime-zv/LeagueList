package life.league.challenge.kotlin.data.network.generator

import kotlinx.coroutines.ExperimentalCoroutinesApi
import life.league.challenge.kotlin.data.network.generator.model.NetworkRequestHeader
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NetworkHeadersGeneratorImplTest {

    private lateinit var settingRepository: SettingRepository

    private lateinit var networkHeadersGeneratorImpl: NetworkHeadersGeneratorImpl


    @Before
    fun setup() {
        settingRepository = mock()
        networkHeadersGeneratorImpl = NetworkHeadersGeneratorImpl(settingRepository)
    }

    @Test
    fun `getHeaders must be contains x-access-code  if token was saved in settingRepository`() {
        val token = "doesn't Important"
        whenever(settingRepository.getToken()).thenReturn(token)
        val expected = listOf(NetworkRequestHeader("x-access-token", token))
        val actual = networkHeadersGeneratorImpl.getHeader()
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `getHeaders return empty if token wasn't saved in settingRepository`() {
        whenever(settingRepository.getToken()).thenReturn(null)
        val expected = emptyList<NetworkRequestHeader>()
        val actual = networkHeadersGeneratorImpl.getHeader()
        Assert.assertEquals(expected, actual)
    }
}