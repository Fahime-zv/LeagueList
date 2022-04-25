package life.league.challenge.kotlin.presentation.ui.screen.postlist

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.repository.NetworkRepository
import life.league.challenge.kotlin.presentation.architucture.BaseViewModelState
import life.league.challenge.kotlin.presentation.architucture.UiState
import life.league.challenge.kotlin.presentation.extenstions.TAG
import life.league.challenge.kotlin.data.network.model.Result

class PostListViewModel(
    private val networkRepository: NetworkRepository
) :
    BaseViewModelState() {

    private val postLiveData = MutableLiveData<List<Post>>()
    fun getPostListLiveData(): LiveData<List<Post>> = postLiveData


    init {
        requestPost()
    }


    @VisibleForTesting
    fun requestPost() {
        viewModelScope.launch {
            uiState(UiState.Loading)
            with(networkRepository.combinePost()) {
                when (this) {
                    is Result.Data -> {
                        postLiveData.postValue(this.model)
                        uiState(UiState.Data)
                        Log.d(this@PostListViewModel.TAG, "requestPosts: ${this.model}")
                    }
                    is Result.NetworkError -> {
                        uiState(UiState.NetworkError)
                    }
                }
            }

        }
    }
}