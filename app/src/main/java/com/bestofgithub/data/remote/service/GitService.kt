package com.bestofgithub.data.remote.service

import com.bestofgithub.data.remote.json.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {
    @GET("orgs/{org}/repos")
    fun getOrgRepos(@Path("org") org: String): Observable<Array<Repository>>
}