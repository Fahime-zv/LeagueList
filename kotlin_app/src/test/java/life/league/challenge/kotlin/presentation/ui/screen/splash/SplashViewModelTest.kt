package life.league.challenge.kotlin.presentation.ui.screen.splash

import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.model.dto.AccountDTO
import life.league.challenge.kotlin.data.network.repository.NetworkRepository
import life.league.challenge.kotlin.presentation.ui.architecture.BaseTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever
import java.util.*
import life.league.challenge.kotlin.data.network.model.Result
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import life.league.challenge.kotlin.presentation.architucture.NavigationCommand
import life.league.challenge.kotlin.presentation.architucture.ToastCommand
import life.league.challenge.kotlin.presentation.architucture.UiState
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import kotlin.test.assertEquals
import kotlin.test.assertTrue


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest : BaseTest() {


    @Mock
    private lateinit var networkRepository: NetworkRepository

    @Mock
    private lateinit var settingRepository: SettingRepository


    private val networkError: String = "Error from server"

    private lateinit var viewModel: SplashViewModel


    private val accountItem = AccountDTO("any")

    @Before
    fun setup() {
        testCoroutineRule.runBlockingTest {
            viewModel = Mockito.spy(
                SplashViewModel(
                    networkRepository = networkRepository,
                    settingRepository = settingRepository,
                    networkErrorMsg = networkError
                )
            )
        }
    }


    @Test
    fun `when networkRepository_login() response is Data then  must be  navigate to PostListFragment`() {
        val direction = SplashFragmentDirections.actionSplashFragmentToPostListFragment()
        testCoroutineRule.runBlockingTest {
            whenever(
                networkRepository.login(
                    userName = "doesn't Important",
                    password = "doesn't Important"
                )
            ).thenReturn(Result.Data(accountItem))
            viewModel.requestLogin(userName = "doesn't Important", password = "doesn't Important")
            verify(viewModel).navigate(eq(direction))
        }
    }

    @Test
    fun `when networkRepository_login() response is NetworkError then must be NetworkErrorMessage`() {
        testCoroutineRule.runBlockingTest {
            whenever(
                networkRepository.login(
                    userName = "doesn't Important",
                    password = "doesn't Important"
                )
            ).thenReturn(Result.NetworkError(Throwable("Not Important")))
            viewModel.requestLogin(userName = "doesn't Important", password = "doesn't Important")
            verify(viewModel).errorToast(networkError)

        }
    }
}