package com.gp.github_starred_repositories

import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GitHubRepositoryApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}