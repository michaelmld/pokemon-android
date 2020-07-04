package com.example.injection

import com.example.pokemon.BaseApplication
import com.example.pokemon.MyExample
import com.example.pokemon.MyExampleImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


//No abstract needed like activity builder
//Add here anything that will not change the entire lifetime of instance
@Module
open class BaseModule {
    @Provides
    @Singleton
    fun provideMyExample(): MyExample {
        return MyExampleImpl()
    }


    //We bound the instance so we can use it here, could even pass in MyExample
    @Provides
    fun getApp(app: BaseApplication):Boolean {
        return app == null
    }
}