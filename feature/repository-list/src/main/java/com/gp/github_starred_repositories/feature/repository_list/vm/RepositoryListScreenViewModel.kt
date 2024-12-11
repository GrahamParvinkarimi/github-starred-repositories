package com.gp.github_starred_repositories.feature.repository_list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gp.github_starred_repositories.feature.repository_list.data.service.GitHubService
import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo
import com.gp.github_starred_repositories.feature.repository_list.domain.model.base.NetworkResult
import com.gp.github_starred_repositories.feature.repository_list.viewstate.RepositoryListScreenViewState
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val finalList = mutableListOf<RepositoryInfo>()

            if (repoListResult is NetworkResult.Success && repoListResult.data != null) {
                for (repo in repoListResult.data) {
                    val ownerResult = gitHubService.retrieveContributors(repo.ownerName, repo.name)
                    if (ownerResult is NetworkResult.Success && ownerResult.data != null) {
                        finalList.add(repo.copy(topContributor = ownerResult.data))
                    }
                }
                _repositoryListScreenViewState.value =
                    RepositoryListScreenViewState.Success(finalList.toList())
            } else {
                _repositoryListScreenViewState.value = RepositoryListScreenViewState.Error()
            }
        }
    }
}