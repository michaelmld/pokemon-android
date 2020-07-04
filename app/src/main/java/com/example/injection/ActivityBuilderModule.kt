package com.example.injection

import com.example.pokemon.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    //Main Activity is a potential client of BaseComponent "service", no longer have to go into Main Activity
    //no longer have to say in MainActivity
    /*
    *   (application as BaseApplication).component.inject(this)
    *
    * if we add more activities, we need to do X amount of ContributesAndroidInjector
    * */
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}

