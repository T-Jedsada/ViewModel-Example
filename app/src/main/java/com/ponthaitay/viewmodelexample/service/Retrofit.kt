package com.ponthaitay.viewmodelexample.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

fun providesUserInfoAPIs(): UserInfoApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(providesOkHttpClient().build())
        .build()
        .create(UserInfoApi::class.java)


fun providesOkHttpClient(): OkHttpClient.Builder {
    val httpClient = OkHttpClient.Builder()
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient
}