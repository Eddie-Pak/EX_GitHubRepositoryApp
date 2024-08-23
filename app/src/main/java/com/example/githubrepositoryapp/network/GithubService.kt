package com.example.githubrepositoryapp.network

import com.example.githubrepositoryapp.model.Repo
import com.example.githubrepositoryapp.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @Headers("Authorization: Bearer {git token}")
    @GET("users/{username}/repos")
    fun listRepos(@Path("username") username: String): Call<List<Repo>>

    @Headers("Authorization: Bearer {git token}")
    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}