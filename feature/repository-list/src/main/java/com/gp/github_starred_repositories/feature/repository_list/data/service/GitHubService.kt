package com.gp.github_starred_repositories.feature.repository_list.data.service

import com.gp.github_starred_repositories.feature.repository_list.domain.model.Contributor
import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo
import com.gp.github_starred_repositories.feature.repository_list.domain.model.base.NetworkResult

interface GitHubService {
    suspend fun retrieveRepositoryList(): NetworkResult<List<RepositoryInfo>>
    suspend fun retrieveContributors(owner: Long, repo: Long): NetworkResult<Contributor>
}