package com.gp.github_starred_repositories.feature.repository_list.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.github_starred_repositories.feature.repository_list.vm.RepositoryListScreenViewModel

@Composable
fun GitHubRepositoryListScreenRoute(
    repositoryListScreenViewModel: RepositoryListScreenViewModel = hiltViewModel()
) {
    //val repositoryListScreenViewState by repositoryListScreenViewModel
    Text("test")
}