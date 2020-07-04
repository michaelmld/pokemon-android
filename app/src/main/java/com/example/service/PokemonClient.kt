package com.example.service

import android.os.Parcelable
import io.reactivex.Observable
import kotlinx.android.parcel.Parcelize
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PokemonClient {
    @GET("api/v2/pokemon")
    fun getPokemon(@Query("offset") offset: String?, @Query("limit") limit: String?): Observable<PokemonContainer>
}

@Parcelize
data class PokemonContainer(var count: Int, var next: String?, var previous: String?, var results: List<PokemonResult?>): Parcelable

@Parcelize
data class PokemonResult(val name: String?, val url: String?): Parcelable

