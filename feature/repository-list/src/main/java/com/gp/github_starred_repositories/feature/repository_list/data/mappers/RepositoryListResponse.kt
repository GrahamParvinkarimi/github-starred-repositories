package com.gp.github_starred_repositories.feature.repository_list.data.mappers

import com.gp.github_starred_repositories.feature.repository_list.domain.model.Contributor
import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryListResponse(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "incomplete_results") val incompleteResults: Boolean,
    @Json(name = "items") val items: List<Repository>
)

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Int,
    @Json(name = "node_id") val nodeId: String,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    val private: Boolean,
    val owner: Owner,
    @Json(name = "html_url") val htmlUrl: String,
    val description: String?,
    val fork: Boolean,
    val url: String,
    @Json(name = "forks_url") val forksUrl: String,
    @Json(name = "keys_url") val keysUrl: String,
    @Json(name = "collaborators_url") val collaboratorsUrl: String,
    @Json(name = "teams_url") val teamsUrl: String,
    @Json(name = "hooks_url") val hooksUrl: String,
    @Json(name = "issue_events_url") val issueEventsUrl: String,
    @Json(name = "events_url") val eventsUrl: String,
    @Json(name = "assignees_url") val assigneesUrl: String,
    @Json(name = "branches_url") val branchesUrl: String,
    @Json(name = "tags_url") val tagsUrl: String,
    @Json(name = "blobs_url") val blobsUrl: String,
    @Json(name = "git_tags_url") val gitTagsUrl: String,
    @Json(name = "git_refs_url") val gitRefsUrl: String,
    @Json(name = "trees_url") val treesUrl: String,
    @Json(name = "statuses_url") val statusesUrl: String,
    @Json(name = "languages_url") val languagesUrl: String,
    @Json(name = "stargazers_url") val stargazersUrl: String,
    @Json(name = "contributors_url") val contributorsUrl: String,
    @Json(name = "subscribers_url") val subscribersUrl: String,
    @Json(name = "subscription_url") val subscriptionUrl: String,
    @Json(name = "commits_url") val commitsUrl: String,
    @Json(name = "git_commits_url") val gitCommitsUrl: String,
    @Json(name = "comments_url") val commentsUrl: String,
    @Json(name = "issue_comment_url") val issueCommentUrl: String,
    @Json(name = "contents_url") val contentsUrl: String,
    @Json(name = "compare_url") val compareUrl: String,
    @Json(name = "merges_url") val mergesUrl: String,
    @Json(name = "archive_url") val archiveUrl: String,
    @Json(name = "downloads_url") val downloadsUrl: String,
    @Json(name = "issues_url") val issuesUrl: String,
    @Json(name = "pulls_url") val pullsUrl: String,
    @Json(name = "milestones_url") val milestonesUrl: String,
    @Json(name = "notifications_url") val notificationsUrl: String,
    @Json(name = "labels_url") val labelsUrl: String,
    @Json(name = "releases_url") val releasesUrl: String,
    @Json(name = "deployments_url") val deploymentsUrl: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "pushed_at") val pushedAt: String,
    @Json(name = "git_url") val gitUrl: String,
    @Json(name = "ssh_url") val sshUrl: String,
    @Json(name = "clone_url") val cloneUrl: String,
    @Json(name = "svn_url") val svnUrl: String,
    val homepage: String?,
    val size: Int,
    @Json(name = "stargazers_count") val stargazersCount: Int,
    @Json(name = "watchers_count") val watchersCount: Int,
    val language: String?,
    @Json(name = "has_issues") val hasIssues: Boolean,
    @Json(name = "has_projects") val hasProjects: Boolean,
    @Json(name = "has_downloads") val hasDownloads: Boolean,
    @Json(name = "has_wiki") val hasWiki: Boolean,
    @Json(name = "has_pages") val hasPages: Boolean,
    @Json(name = "has_discussions") val hasDiscussions: Boolean,
    @Json(name = "forks_count") val forksCount: Int,
    @Json(name = "mirror_url") val mirrorUrl: String?,
    val archived: Boolean,
    val disabled: Boolean,
    @Json(name = "open_issues_count") val openIssuesCount: Int,
    val license: License?,
    @Json(name = "allow_forking") val allowForking: Boolean,
    @Json(name = "is_template") val isTemplate: Boolean,
    @Json(name = "web_commit_signoff_required") val webCommitSignoffRequired: Boolean,
    val topics: List<String>,
    val visibility: String,
    val forks: Int,
    @Json(name = "open_issues") val openIssues: Int,
    val watchers: Int,
    @Json(name = "default_branch") val defaultBranch: String,
    val score: Double
)

@JsonClass(generateAdapter = true)
data class Owner(
    val login: String,
    val id: Int,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "gravatar_id") val gravatarId: String?,
    val url: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "followers_url") val followersUrl: String,
    @Json(name = "following_url") val followingUrl: String,
    @Json(name = "gists_url") val gistsUrl: String,
    @Json(name = "starred_url") val starredUrl: String,
    @Json(name = "subscriptions_url") val subscriptionsUrl: String,
    @Json(name = "organizations_url") val organizationsUrl: String,
    @Json(name = "repos_url") val reposUrl: String,
    @Json(name = "events_url") val eventsUrl: String,
    @Json(name = "received_events_url") val receivedEventsUrl: String,
    val type: String,
    @Json(name = "user_view_type") val userViewType: String?,
    @Json(name = "site_admin") val siteAdmin: Boolean
)

@JsonClass(generateAdapter = true)
data class License(
    val key: String,
    val name: String,
    @Json(name = "spdx_id") val spdxId: String,
    val url: String?,
    @Json(name = "node_id") val nodeId: String
)

/*
    Maps the RepositoryListResponse to what is needed within the domain for mapping to the UI
 */
fun RepositoryListResponse.mapToDomain(): List<RepositoryInfo> {
    return this.items.map {
        RepositoryInfo(
            name = it.name,
            ownerName = it.owner.login,
            fullName = it.fullName
        )
    }
}

/*
    Maps the Owner to what is needed within the domain for mapping to the UI
 */
fun List<Owner>.mapToDomain(): Contributor {
    return Contributor(
        name = this[0].login
    )
}

