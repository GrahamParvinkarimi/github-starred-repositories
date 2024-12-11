package com.gp.github_starred_repositories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberGitHubRepositoryAppState(
    navController: NavHostController = rememberNavController()
): GitHubRepositoryAppState {
    return remember(
        navController
    ) {
        GitHubRepositoryAppState(
            navController = navController
        )
    }
}

@Stable
class GitHubRepositoryAppState(
    val navController: NavHostController
)