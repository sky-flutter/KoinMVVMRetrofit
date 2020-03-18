package com.example.koinmvvmretrofit.data.rest

import com.example.koinmvvmretrofit.data.model.Repo
import io.reactivex.Single
import retrofit2.http.GET

interface RepoService {
    @GET("orgs/Google/repos")
    fun getRepositories(): Single<List<Repo>>
}