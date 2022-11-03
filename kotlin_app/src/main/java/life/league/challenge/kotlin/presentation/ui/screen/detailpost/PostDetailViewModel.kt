package life.league.challenge.kotlin.presentation.ui.screen.detailpost

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.model.dao.UserDAO
import life.league.challenge.kotlin.presentation.architucture.BaseViewModelState

class PostDetailViewModel(private val post: Post) :
    BaseViewModelState() {

    private val postLiveData = MutableLiveData<UserDAO>()
    fun getUserLiveData(): LiveData<UserDAO> = postLiveData

    init {
        postLiveData.postValue(post.user)
    }


}