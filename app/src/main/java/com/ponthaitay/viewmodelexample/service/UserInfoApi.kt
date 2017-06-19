package com.ponthaitay.viewmodelexample.service

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoApi {
    @GET("users/{username}")
    fun getUserInfoGitHub(@Path("username") username: String): Observable<Response<UserInfoDao>>
}