package com.gp.github_starred_repositories.feature.repository_list.viewstate

import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo

sealed interface RepositoryListScreenViewState {

    /**
     * Initial state when the repository screen is first shown
     */
    data object Initial : RepositoryListScreenViewState

    /**
     * Loading state
     */
    data object Loading : RepositoryListScreenViewState

    /**
     * Repository data has been loaded
     */
    data class Success(val data: List<RepositoryInfo>?) : RepositoryListScreenViewState

    /**
     * Error state, if an error was received loading the data
     */
    data class Error(val errorMessage: String? = null) : RepositoryListScreenViewState
}