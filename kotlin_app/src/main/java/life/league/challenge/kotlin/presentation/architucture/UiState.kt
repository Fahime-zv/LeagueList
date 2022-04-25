package life.league.challenge.kotlin.presentation.architucture

sealed class UiState {
    object Loading : UiState()
    object Data : UiState()
    object NetworkError : UiState()

    override fun toString(): String {
        return when (this) {
            Data -> "Data"
            Loading -> "Loading"
            NetworkError -> "NetworkError"
        }
    }
}