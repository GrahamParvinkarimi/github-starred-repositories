package com.gp.github_starred_repositories.feature.repository_list.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gp.github_starred_repositories.feature.repository_list.data.service.GitHubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryListScreenViewModel @Inject constructor(
    private val gitHubService: GitHubService
): ViewModel() {
    init {
        viewModelScope.launch {
            val repoListResponse = gitHubService.retrieveRepositoryList()
            println(repoListResponse)
        }
    }
}