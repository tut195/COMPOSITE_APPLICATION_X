package com.babenkovladimir.composite_application_x.rx.rxjava_github

//Create Data Object

data class GitHubRepo(
    var id: Int,
    val name: String,
    val htmlUrl: String,
    val description: String,
    val language: String,
    val stargazersCount: Int
)