package com.gp.github_starred_repositories.feature.repository_list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gp.github_starred_repositories.feature.repository_list.data.service.GitHubService
import com.gp.github_starred_repositories.feature.repository_list.domain.model.base.NetworkResult
import com.gp.github_starred_repositories.feature.repository_list.viewstate.RepositoryListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListScreenViewModel @Inject constructor(
    private val gitHubService: GitHubService
) : ViewModel() {

    private val _repositoryListScreenViewState: MutableStateFlow<RepositoryListScreenViewState> =
        MutableStateFlow(RepositoryListScreenViewState.Initial)

    val repositoryListScreenViewState: StateFlow<RepositoryListScreenViewState> =
        _repositoryListScreenViewState

    init {
        loadRepositoryData()
    }

    fun loadRepositoryData() {
        _repositoryListScreenViewState.value = RepositoryListScreenViewState.Loading

        viewModelScope.launch {
            val repoListResult = gitHubService.retrieveRepositoryList()

            if (repoListResult is NetworkResult.Success && repoListResult.data != null) {
                // Launch the contributor calls for the top 100 repositories concurrently and wait for the result
                val finalList = repoListResult.data.map { repo ->
                        async {
                            val ownerResult = gitHubService.retrieveContributors(repo.ownerName, repo.name)
                            if (ownerResult is NetworkResult.Success && ownerResult.data != null) {
                                repo.copy(topContributor = ownerResult.data)
                            } else {
                                repo // Return the repo without a top contributor if the call fails
                            }
                        }
                    }.awaitAll()

                _repositoryListScreenViewState.value =
                    RepositoryListScreenViewState.Success(finalList.toList())
            } else {
                _repositoryListScreenViewState.value = RepositoryListScreenViewState.Error()
            }
        }
    }
}