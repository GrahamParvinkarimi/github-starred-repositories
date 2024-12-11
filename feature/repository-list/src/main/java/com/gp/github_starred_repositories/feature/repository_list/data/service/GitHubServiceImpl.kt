package com.gp.github_starred_repositories.feature.repository_list.data.service

import com.gp.github_starred_repositories.feature.repository_list.data.mappers.Owner
import com.gp.github_starred_repositories.feature.repository_list.data.mappers.RepositoryListResponse
import com.gp.github_starred_repositories.feature.repository_list.data.mappers.mapToDomain
import com.gp.github_starred_repositories.feature.repository_list.data.util.NetworkConstants
import com.gp.github_starred_repositories.feature.repository_list.domain.model.Contributor
import com.gp.github_starred_repositories.feature.repository_list.domain.model.RepositoryInfo
import com.gp.github_starred_repositories.feature.repository_list.domain.model.base.NetworkResult
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class GitHubServiceImpl @Inject constructor(
    retrofit: Retrofit
): GitHubService {

    private val api = retrofit.create<GitHubApi>()

    interface GitHubApi {
        @GET("search/repositories")
        suspend fun getRepositoryList(@Query("q") query: String, @Query("per_page") perPage: String): Response<RepositoryListResponse>

        @GET("repos/{owner}/{repo}/contributors")
        suspend fun getRepositoryOwners(@Path("owner") owner: String, @Path("repo") repo: String): Response<List<Owner>>
    }

    override suspend fun retrieveRepositoryList(): NetworkResult<List<RepositoryInfo>> {
        try {
            val response = api.getRepositoryList(query = "stars:>0", perPage = "100")
            return if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(data = response.body()?.mapToDomain())
            } else {
                NetworkResult.Error(errorMessage = response.message())
            }
        } catch (e: Exception) {
            println(e)
            return NetworkResult.Error(errorMessage = e.message ?: NetworkConstants.PARSING_ERROR_TEXT)
        }
    }

    override suspend fun retrieveContributors(
        owner: String,
        repo: String
    ): NetworkResult<Contributor> {
        try {
            val response = api.getRepositoryOwners(owner = owner, repo = repo)
            return if (response.isSuccessful && response.body() != null) {
                NetworkResult.Success(data = response.body()?.mapToDomain())
            } else {
                NetworkResult.Error(errorMessage = response.message())
            }
        } catch (e: Exception) {
            println(e)
            return NetworkResult.Error(errorMessage = e.message ?: NetworkConstants.PARSING_ERROR_TEXT)
        }
    }
}