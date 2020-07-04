package com.example.injection

import com.example.service.PokemonClient
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
open class NetworkModule {

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    fun provideRetrofitInterface(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Provides
    fun providePokemonAPI(retroFitBuilder: Retrofit.Builder): PokemonClient {
        val retrofit = retroFitBuilder
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(
                    Schedulers.io()
                )
            )
            .baseUrl("https://pokeapi.co/").build()
        return retrofit.create(PokemonClient::class.java)
    }
}