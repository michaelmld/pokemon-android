package com.example.pokemon.models

//Essentially copying the data models from my iOS project

data class Collection(var id: String?, var name: String?, var words: List<Word>)

data class Word(var id: String?, var word: String?, var definition: String?)
