package life.league.challenge.kotlin.data.network.model

sealed class Result<out T : Any> {
    data class Data<out T : Any>(val model: T) : Result<T>()
    data class NetworkError(val e: Throwable) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Data -> "Data(model=$model)"
            is NetworkError -> "NetworkError(e= $e)"
        }
    }
}