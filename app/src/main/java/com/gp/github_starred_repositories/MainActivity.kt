package com.gp.github_starred_repositories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.gp.github_starred_repositories.ui.theme.GitHubStarredRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GitHubStarredRepositoriesTheme {
                val appState = rememberGitHubRepositoryAppState()
                GitHubRepositoryApp(appState = appState)
            }
        }
    }
}