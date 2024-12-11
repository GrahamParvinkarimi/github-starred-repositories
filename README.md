# Graham Parvinkarimi's GitHub Repository List

## Project Overview

### Architecture

* MVVM
* CLEAN Architecture principles

### Dependencies

* Android Architecture Components (MVVM)
* Material 3 Jetpack Compose UI Components
* Jetpack Navigation Compose
* Hilt/Dagger for Dependency Injection
* Retrofit & OkHttp for HTTP calls
* Moshi for JSON parsing
* Coroutines for concurrency & asynchronous operations

### Setup Instructions

* Clone the https://github.com/GrahamParvinkarimi/github-starred-repositories repository
* Open the project in the latest version of Android Studio (Android Studio Ladybug | 2024.2.1 Patch 3)
* Add your GitHub Auth token in the OkHttpClient within `GitHubNetworkModule`:
    * `.header("Authorization", "Bearer {Add your GH Auth Token here to avoid rate limits}")`
    * If you don't add this, your IP address will be rate-limited to 60 requests per hour, and you will see less than 100 repositories listed
* Build and run the project (Gradle sync if needed)

### Features

* The list of repositories is loaded from `GET https://api.github.com/search/repositories?q=stars:%3E0&per_page=100` when the app is opened
* A call to `GET https://api.github.com/repos/{owner}/{repo}/contributors` is made concurrently using `async/await` for each of the top 100 repositories (then the app waits for all of the calls to finish)
* A loading spinner is shown until the above service calls are finished running
* The list of repositories & contributors is shown if the `GET repositories` call was successful, or an error retry tombstone is shown in the case of an error (allowing the user to retry the call)
* If the top contributor call returned an error for a given repository, "** FAILED TO LOAD **" is displayed for the contributor name (this can happen in the case when there are too many contributors and the endpoint returns a 403)

## Modules

* `:app` module for the application's scaffolding
* `:feature:repository-list` module for the Repository List feature