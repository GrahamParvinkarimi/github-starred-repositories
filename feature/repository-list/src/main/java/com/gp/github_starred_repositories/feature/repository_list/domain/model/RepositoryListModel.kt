package com.gp.github_starred_repositories.feature.repository_list.domain.model

data class RepositoryInfo(
    val name: String,
    val ownerName: String,
    val fullName: String,
    val topContributor: Contributor? = null
)

data class Contributor(
    val name: String
)