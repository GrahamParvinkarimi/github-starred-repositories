package com.gp.github_starred_repositories.feature.repository_list.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import com.gp.github_starred_repositories.feature.repository_list.R
import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo
import com.gp.github_starred_repositories.feature.repository_list.ui.constants.Dimens
import com.gp.github_starred_repositories.feature.repository_list.util.RepositoryListTextUtils
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

@Composable
fun RepositoryList(repositories: List<RepositoryInfo>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = Dimens.padding_8)
    ) {
        items(
            repositories.size
        ) { repo ->
            RepositoryCard(
                repository = repositories[repo]
            )
        }
    }
}

@Composable
fun RepositoryCard(
    repository: RepositoryInfo
) {

    Card(
        modifier = Modifier
            .padding(horizontal = Dimens.padding_16, vertical = Dimens.padding_4),
        colors = CardDefaults.cardColors(
            MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16)
        ) {
            Row(
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.padding(top = Dimens.padding_4)
                ) {
                    // Leading Icon
                    Icon(
                        imageVector = Icons.Default.Star,  // Repository icon
                        contentDescription = stringResource(R.string.repository_icon_content_description)
                    )
                }

                Spacer(modifier = Modifier.width(Dimens.padding_16))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Repository title
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                R.string.repository_title_text,
                                repository.fullName
                            ),
                            fontSize = Dimens.font_size_14,
                            color = Color.Black,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                R.string.top_contributor_text,
                                RepositoryListTextUtils.getRepositoryOwnerName(repository)
                            ),
                            fontSize = Dimens.font_size_12,
                            color = Color.Gray
                        )
                    }

                    Spacer(modifier = Modifier.height(Dimens.padding_8))
                }
            }
        }
    }
}

@Composable
fun RetryTombstone(
    errorMessage: String,
    onRetry: () -> Unit, // Retry action callback
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimens.padding_16),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Error background color
        ),
        shape = RoundedCornerShape(Dimens.padding_8), // Rounded corners for the card
        elevation = CardDefaults.cardElevation(Dimens.padding_4)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.padding_16),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Error icon or any decorative icon if needed
            Icon(
                imageVector = Icons.Default.Build,
                contentDescription = stringResource(R.string.error_icon_content_description),
                tint = MaterialTheme.colorScheme.onErrorContainer,
                modifier = Modifier.size(Dimens.size_48)
            )

            Spacer(modifier = Modifier.height(Dimens.padding_8))

            // Error message
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = Dimens.padding_16)
            )

            Spacer(modifier = Modifier.height(Dimens.padding_16))

            // Retry Button
            Button(
                onClick = onRetry,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = stringResource(R.string.retry_text))
            }
        }
    }
}