package com.gp.github_starred_repositories.feature.repository_list.domain.model.base

/*
    Base NetworkResult class for service call results
    Could extract this to a core/domain module if the project grows
 */
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T? = null) : NetworkResult<T>()
    data class Error(val errorMessage: String?) : NetworkResult<Nothing>()
}