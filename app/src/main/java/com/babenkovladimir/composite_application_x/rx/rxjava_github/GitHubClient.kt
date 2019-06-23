package com.babenkovladimir.composite_application_x.rx.rxjava_github

import androidx.annotation.NonNull
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class GitHubClient private constructor() {
    private val gitHubService: GitHubService

    init {
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        gitHubService = retrofit.create(GitHubService::class.java)
    }

    companion object {

        private const val GITHUB_BASE_URL = "https://api.github.com"

        private var instance: GitHubClient? = null

        @Synchronized
        fun getInstance(): GitHubClient {
            if (instance == null) {
                instance = GitHubClient()
            }
            return instance!!
        }
    }


    fun getStarredRepos(@NonNull userName: String): Observable<List<GitHubRepo>> {
        return gitHubService.getStarredRepositories(userName)
    }
}