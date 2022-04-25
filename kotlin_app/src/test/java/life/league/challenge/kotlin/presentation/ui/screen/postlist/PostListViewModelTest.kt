package life.league.challenge.kotlin.presentation.ui.screen.postlist

import androidx.lifecycle.Observer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.model.Result
import life.league.challenge.kotlin.data.network.model.dao.UserDAO
import life.league.challenge.kotlin.data.network.model.dto.AccountDTO
import life.league.challenge.kotlin.data.network.repository.NetworkRepository
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import life.league.challenge.kotlin.presentation.architucture.UiState
import life.league.challenge.kotlin.presentation.ui.architecture.BaseTest
import life.league.challenge.kotlin.presentation.ui.screen.splash.SplashViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PostListViewModelTest : BaseTest() {


    @Mock
    private lateinit var postLiveData: Observer<List<Post>>

    @Mock
    private lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: PostListViewModel

    private val postItemList = listOf(
        Post(
            1, UserDAO(
                1,
                UserDAO.AvatarDAO("", "", ""),
                "",
                "",
                "",
                UserDAO.AddressDAO("", "", "", "", UserDAO.GeoDAO("", "")),
                "",
                "",
                UserDAO.CompanyDAO("", "", "")
            ), "", ""
        )
    )


    @Before
    fun setup() {
        testCoroutineRule.runBlockingTest {
            viewModel = Mockito.spy(
                PostListViewModel(
                    networkRepository = networkRepository
                )
            )
        }
        viewModel.getPostListLiveData().observeForever(postLiveData)

    }

    @Test
    fun `when networkRepository_combinePost() request's called then it has result  finally return postItem`() {
        testCoroutineRule.runBlockingTest {
            whenever(networkRepository.combinePost()).thenReturn(Result.Data(postItemList))
            viewModel.requestPost()
            verify(postLiveData).onChanged(postItemList)
        }
    }

    @Test
    fun `when networkRepository_combinePost() response is Data then UiState must be Data`() {
        testCoroutineRule.runBlockingTest {
            whenever(networkRepository.combinePost()).thenReturn(Result.Data(postItemList))
            viewModel.requestPost()
            verify(viewModel).uiState(eq(UiState.Data))
        }
    }

    @Test
    fun `when homesRepository_homes()response is Data then UiState must be NetworkError`() {
        testCoroutineRule.runBlockingTest {
            whenever(networkRepository.combinePost()).thenReturn(Result.NetworkError(Throwable("Not Important")))
            viewModel.requestPost()
            verify(viewModel).uiState(eq(UiState.NetworkError))
        }
    }

}
