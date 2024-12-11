package com.gp.github_starred_repositories

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.gp.github_starred_repositories.ui.theme.GitHubStarredRepositoriesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.BLACK
            )
        )
        setContent {
            GitHubStarredRepositoriesTheme {
                Box(Modifier.safeDrawingPadding()) {
                    val appState = rememberGitHubRepositoryAppState()
                    GitHubRepositoryApp(appState = appState)
                }
            }
        }
    }
}