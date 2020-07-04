package com.example.injection

import com.example.pokemon.BaseApplication
import com.example.pokemon.viewmodels.CollectionsViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//Components are services, activities/fragments are clients

//Because a referenced module provides a singleton, the Component itself must be flagged with the Singleton annotation.
//Base component OWNS the singleton scope
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, BaseModule::class, NetworkModule::class])
interface BaseComponent: AndroidInjector<BaseApplication> {

    //No longer need to do this with dagger android
    //fun inject(mainActivity: MainActivity?)

    fun inject(vm: CollectionsViewModel)

    @Component.Builder
    interface Builder{

        //When we bind the instance, we can pass it anywhere into our components/moduless
        @BindsInstance
        fun app(baseApp: BaseApplication): Builder
        //I could add other methods here that return builder if i want something to live the entire time?
        fun build(): BaseComponent
    }
}