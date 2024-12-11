package com.gp.github_starred_repositories

import androidx.compose.runtime.Composable
import com.gp.github_starred_repositories.navigation.GitHubRepositoryNavHost

@Composable
fun GitHubRepositoryApp(
    appState: GitHubRepositoryAppState
) {
    /*
       Scaffold isn't needed for now since a `topBar` / `bottomBar` aren't needed
       at the moment
    */
    GitHubRepositoryNavHost(
        appState = appState
    )
}