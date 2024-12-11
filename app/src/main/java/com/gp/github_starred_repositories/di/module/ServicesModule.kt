package com.gp.github_starred_repositories.di.module

import com.gp.github_starred_repositories.feature.repository_list.data.service.GitHubService
import com.gp.github_starred_repositories.feature.repository_list.data.service.GitHubServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {
    @Binds
    abstract fun bindsGitHubService(GitHubServiceImpl: GitHubServiceImpl): GitHubService
}
