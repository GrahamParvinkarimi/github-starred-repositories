package com.gp.github_starred_repositories.feature.repository_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.gp.github_starred_repositories.feature.repository_list.ui.GitHubRepositoryListScreenRoute
import kotlinx.serialization.Serializable

@Serializable
object GitHubRepositoryList

fun NavGraphBuilder.gitHubRepositoryNavGraph(
    navController: NavController
) {
    // Nav Controller isn't used yet, but can be used if additional screens are added
    composable<GitHubRepositoryList> {
        GitHubRepositoryListScreenRoute()
    }
}