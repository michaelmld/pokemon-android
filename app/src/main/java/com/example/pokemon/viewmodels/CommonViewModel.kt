package com.example.pokemon.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.pokemon.BaseApplication

abstract class CommonViewModel(application: Application) : AndroidViewModel(application) {
    val baseApplication = application as BaseApplication
}