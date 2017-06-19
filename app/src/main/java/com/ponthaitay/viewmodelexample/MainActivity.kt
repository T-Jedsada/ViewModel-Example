package com.ponthaitay.viewmodelexample

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View.GONE
import com.ponthaitay.viewmodelexample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : LifecycleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        ViewModelProviders.of(this)
                .get(MainActivityViewModel::class.java)
                .getUserInfo("t-jedsada")
                .observe(this, Observer {
                    pb.visibility = GONE
                    binding.userInfo = it
                })
    }
}