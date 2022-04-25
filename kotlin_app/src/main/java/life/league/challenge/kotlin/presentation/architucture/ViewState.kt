package life.league.challenge.kotlin.presentation.architucture

interface ViewState {
    fun onLoading()
    fun onData()
    fun onNetworkError()
    fun onApiError(msg: String)
}