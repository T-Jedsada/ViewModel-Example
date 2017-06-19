package com.ponthaitay.viewmodelexample

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ponthaitay.viewmodelexample.service.UserInfoApi
import com.ponthaitay.viewmodelexample.service.UserInfoDao
import com.ponthaitay.viewmodelexample.service.providesOkHttpClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel : ViewModel() {
    private var liveDataUserInfo: MutableLiveData<UserInfoDao>? = null

    fun getUserInfo(username: String): LiveData<UserInfoDao> {
        if (liveDataUserInfo == null) {
            liveDataUserInfo = MutableLiveData()
            providesUserInfoAPIs().getUserInfoGitHub(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it.isSuccessful) liveDataUserInfo?.value = it?.body()
                        else liveDataUserInfo?.value?.name = it?.message()!!
                    }, {
                        liveDataUserInfo?.value?.name = it?.message!!
                    })
        }
        return liveDataUserInfo as MutableLiveData<UserInfoDao>
    }

    fun providesUserInfoAPIs(): UserInfoApi = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(providesOkHttpClient().build())
            .build()
            .create(UserInfoApi::class.java)
}