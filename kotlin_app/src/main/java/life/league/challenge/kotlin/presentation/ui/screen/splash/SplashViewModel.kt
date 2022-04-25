package life.league.challenge.kotlin.presentation.ui.screen.splash

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.R
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.repository.NetworkRepository
import life.league.challenge.kotlin.presentation.architucture.BaseViewModelState
import life.league.challenge.kotlin.presentation.architucture.UiState
import life.league.challenge.kotlin.data.sharedpreferences.SettingRepository
import life.league.challenge.kotlin.presentation.extenstions.TAG
import life.league.challenge.kotlin.data.network.model.Result
import life.league.challenge.kotlin.data.network.model.dto.AccountDTO

class SplashViewModel(
    private val networkRepository: NetworkRepository,
    private val settingRepository: SettingRepository,
    private val networkErrorMsg: String
) : BaseViewModelState() {

    init {
        requestLogin("hello", "world")
    }

    @VisibleForTesting
    fun requestLogin(userName: String, password: String) {
        viewModelScope.launch {
            uiState(UiState.Loading)
            with(networkRepository.login(userName, password)) {
                when (this) {
                    is Result.Data -> {
//                        Log.d(this@SplashViewModel.TAG, "requestLogin: ${this.model}")
                        if (this.model.apiKey != null)
                            settingRepository.saveToken(this.model.apiKey)
                        navigate(SplashFragmentDirections.actionSplashFragmentToPostListFragment())
                    }

                    is Result.NetworkError -> {
                        errorToast(networkErrorMsg)
                    }
                }
            }

        }
    }
}