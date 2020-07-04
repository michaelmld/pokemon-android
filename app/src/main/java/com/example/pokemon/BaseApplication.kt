package com.example.pokemon

import com.example.injection.BaseComponent
import com.example.injection.DaggerBaseComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class BaseApplication : DaggerApplication() {

    lateinit var component: BaseComponent

    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        component = DaggerBaseComponent.builder().app(this).build()
        return component
    }
}