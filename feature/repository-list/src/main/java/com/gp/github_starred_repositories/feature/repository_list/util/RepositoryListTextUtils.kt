package com.gp.github_starred_repositories.feature.repository_list.util

import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo

object RepositoryListTextUtils {
    private const val FAILED_TO_LOAD_CONTRIBUTOR_TEXT = "** FAILED TO LOAD **"

    fun getRepositoryOwnerName(repository: RepositoryInfo?): String {
        return repository?.topContributor?.name ?: FAILED_TO_LOAD_CONTRIBUTOR_TEXT
    }
}