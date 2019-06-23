package com.babenkovladimir.composite_application_x.rx.rxjava_github

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

// Create the GitHubService interface.
// We will pass this interface into Retrofit and Retrofit will create an implementation of GitHubService.

interface GitHubService {

    @GET("/users/{user}/starred")
    fun getStarredRepositories(@Path("user") username: String): Observable<List<GitHubRepo>>
}