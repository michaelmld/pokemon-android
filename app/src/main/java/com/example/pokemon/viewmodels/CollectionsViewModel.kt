package com.example.pokemon.viewmodels

import android.app.Application
import com.example.service.PokemonClient
import com.example.service.PokemonContainer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CollectionsViewModel(application: Application) : CommonViewModel(application) {

    var text: String? = null

    @Inject
    lateinit var pokemonClient: PokemonClient

    init {
        this.baseApplication.component.inject(this)
    }

    fun load() {
        //If you don’t specify threading in RxJava (if you don’t specify subscribeOn, observeOn or both),
        // the data will be emitted and processed by the current scheduler/thread (usually the main thread)
        //Scheduelrs.io == background thread
        pokemonClient.getPokemon()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({result: PokemonContainer? ->
                println("thread ========="+Thread.currentThread().name)
                println(result)
            }, ::onError)
    }

    fun onError(error: Throwable){
        println(error.toString())
    }

}