package com.example.pokemon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.FragmentCollectionsBinding
import com.example.pokemon.models.Word
import com.example.pokemon.viewmodels.CollectionsViewModel
import kotlin.random.Random
import com.example.pokemon.models.Collection
import com.example.service.PokemonClient
import javax.inject.Inject

class CollectionsFragment: Fragment() {

    // Use the 'by viewModels()' Kotlin property delegate
    // from the activity-ktx artifact
    val model: CollectionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val c1 = Collection("id", "hi murphy", emptyList<Word>())
        val c2 = Collection("id2","hi clara", emptyList<Word>())
        val c3 = Collection("id3","i like animals", emptyList<Word>())
        model.load()

        val data = mutableListOf<Collection>(c1,c2,c3)
        val collectionAdapter = CollectionAdapter(data, findNavController())

        return FragmentCollectionsBinding.inflate(inflater, container, false).apply {
            collectionRecyclerView.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this.context)
                adapter = collectionAdapter
            }

            addCollectionSubmitListener = View.OnClickListener {
                val randomInt = Random.nextInt(0, 100)
                val randomCollection = Collection(
                    "id" + randomInt.toString(),
                    "i like animals " + randomInt.toString(),
                    emptyList<Word>()
                )
                data.add(randomCollection)
                collectionAdapter.notifyItemInserted(data.size - 1)
            }
        }.root
    }
}