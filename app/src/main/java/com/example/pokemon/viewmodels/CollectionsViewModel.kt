package com.example.pokemon.viewmodels

import android.app.Application
import android.net.UrlQuerySanitizer
import com.example.service.PokemonClient
import com.example.service.PokemonContainer
import io.reactivex.subjects.BehaviorSubject
import java.net.URI
import javax.inject.Inject

class CollectionsViewModel(application: Application) : CommonViewModel(application) {

    var text: String? = null

    @Inject
    lateinit var pokemonClient: PokemonClient

    init {
        this.baseApplication.component.inject(this)
    }

    fun load() {

        //Weird that im using behavior subject instead of publish subject
        val stream = BehaviorSubject.createDefault(PokemonContainer(0, null, null, emptyList()))

        stream.flatMap { it ->
            var offset = ""
            var limit = ""
            if (it.next != null) {
                val sanitizer = UrlQuerySanitizer()
                sanitizer.setAllowUnregisteredParamaters(true)
                sanitizer.parseUrl(it.next)

                var maybeOffset : String? = sanitizer.getValue("offset")
                maybeOffset?.let{
                    offset = it
                }

            }

            pokemonClient.getPokemon(offset,limit).doOnNext {
                if (it.next != null) {
                    stream.onNext(it)
                } else {
                    stream.onComplete()
                }
            }
        }.subscribe {
            println("OUT SIDE SUBSCRIBE thread ========="+Thread.currentThread().name)
            println(it)
        }

        //If you don’t specify threading in RxJava (if you don’t specify subscribeOn, observeOn or both),
        // the data will be emitted and processed by the current scheduler/thread (usually the main thread)
        //Scheduelrs.io == background thread
//        pokemonClient.getPokemon("20", "20")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({result: PokemonContainer? ->
//                println("thread ========="+Thread.currentThread().name)
//                println(result)
//            }, ::onError)
    }


//    fun all(): Observable<T> {
//        val tokenStream = BehaviorSubject.createDefault(Token.startToken())
//        return tokenStream.flatMap { token ->
//            remoteData(token).doOnNext { w ->
//                if (w.getToken().hasMore()) {
//                    tokenStream.onNext(w.getToken())
//                } else {
//                    tokenStream.onComplete()
//                }
//            }
//        }
//    }

    fun onError(error: Throwable){
        println(error.toString())
    }

}