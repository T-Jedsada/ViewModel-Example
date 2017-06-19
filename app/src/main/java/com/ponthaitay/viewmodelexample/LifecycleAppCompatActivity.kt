package com.ponthaitay.viewmodelexample

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity

open class LifecycleAppCompatActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val mRegister = LifecycleRegistry(this)

    override fun getLifecycle(): LifecycleRegistry = mRegister
}
