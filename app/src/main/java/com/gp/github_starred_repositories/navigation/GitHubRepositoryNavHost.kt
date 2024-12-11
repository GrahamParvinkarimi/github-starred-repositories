package com.gp.github_starred_repositories.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.gp.github_starred_repositories.GitHubRepositoryAppState
import com.gp.github_starred_repositories.feature.repository_list.navigation.GitHubRepositoryList
import com.gp.github_starred_repositories.feature.repository_list.navigation.gitHubRepositoryNavGraph

/**
 * Top-level navigation graph
 */
@Composable
fun GitHubRepositoryNavHost(
    appState: GitHubRepositoryAppState
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = GitHubRepositoryList
    ) {
        gitHubRepositoryNavGraph(
            navController
        )
    }
}