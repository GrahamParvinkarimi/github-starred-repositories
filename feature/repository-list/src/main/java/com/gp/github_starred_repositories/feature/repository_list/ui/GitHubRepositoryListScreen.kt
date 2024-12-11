package com.gp.github_starred_repositories.feature.repository_list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.github_starred_repositories.feature.repository_list.R
import com.gp.github_starred_repositories.feature.repository_list.viewstate.RepositoryListScreenViewState
import com.gp.github_starred_repositories.feature.repository_list.vm.RepositoryListScreenViewModel

@Composable
fun GitHubRepositoryListScreenRoute(
    repositoryListScreenViewModel: RepositoryListScreenViewModel = hiltViewModel()
) {
    val repositoryListScreenViewState by repositoryListScreenViewModel.repositoryListScreenViewState.collectAsState()

    GitHubRepositoryListScreen(
        repositoryListScreenViewState = repositoryListScreenViewState,
        retryNetworkCall = repositoryListScreenViewModel::loadRepositoryData
    )
}

@Composable
fun GitHubRepositoryListScreen(
    repositoryListScreenViewState: RepositoryListScreenViewState,
    retryNetworkCall: () -> Unit
) {
    when (repositoryListScreenViewState) {
        RepositoryListScreenViewState.Initial, RepositoryListScreenViewState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        }

        is RepositoryListScreenViewState.Success -> {
            RepositoryList(
                repositories = repositoryListScreenViewState.data ?: listOf()
            )
        }

        is RepositoryListScreenViewState.Error -> {
            RetryTombstone(
                errorMessage = stringResource(R.string.default_network_error_message),
                onRetry = retryNetworkCall
            )
        }
    }
}