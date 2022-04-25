package life.league.challenge.kotlin.data.network.repository

import life.league.challenge.kotlin.core.entity.Post
import life.league.challenge.kotlin.data.network.api.Service
import life.league.challenge.kotlin.data.network.api.login
import life.league.challenge.kotlin.data.network.model.dto.AccountDTO
import life.league.challenge.kotlin.data.network.model.dao.PostDAO
import life.league.challenge.kotlin.data.network.model.dao.UserDAO
import life.league.challenge.kotlin.data.network.model.Result

class NetworkRepository(private val service: Service) : BaseRepository() {

    suspend fun login(userName: String, password: String): Result<AccountDTO> {
        return safeApiCall {
            service.api.login(username = userName, password = password)
        }
    }

    private suspend fun users(): Result<List<UserDAO>> {
        return safeApiCall {
            service.api.users()
        }
    }

    private suspend fun posts(): Result<List<PostDAO>> {
        return safeApiCall {
            service.api.posts()
        }
    }

    suspend fun combinePost(): Result<List<Post>> {
        when (val users = users()) {
            is Result.Data -> {
                when (val posts = posts()) {
                    is Result.Data -> {
                        return Result.Data(posts.model.map { postDAO ->
                            Post(
                                id = postDAO.id,
                                user = users.model.find { it.id == postDAO.userId }
                                    ?: return Result.NetworkError(IllegalArgumentException("User not found!")),
                                title = postDAO.title,
                                description = postDAO.body
                            )
                        })
                    }

                    is Result.NetworkError -> {
                        return Result.NetworkError(posts.e)
                    }
                }
            }

            is Result.NetworkError -> {
                return Result.NetworkError(users.e)
            }
        }
    }


}