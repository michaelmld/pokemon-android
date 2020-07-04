package com.example.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.models.Collection

class CollectionAdapter(private val data: List<Collection>, private val navController: NavController): RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder>() {

    class CollectionViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var textView: TextView
        var layout: View
        init {
            layout = v
            textView = v.findViewById(R.id.myTextView)
        }
    }

    //Parent should be Recycler View
    //The viewType parameter is used when there are multiple views in the same RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        /*
        * The layout inflater knows how to create views from XML layouts.
        * The context contains information on how to correctly inflate the view.
        * In an adapter for a recycler view, you always pass in the context of the parent view group, which is the RecyclerView.
        * */
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.collection_view, parent, false) as View
        return CollectionViewHolder(view)
    }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.name //just using name for now

        holder.textView.setOnClickListener {
            val toSecondFragment =
                CollectionsFragmentDirections.actionCollectionsFragmentToSecondFragment(1)
            navController.navigate(toSecondFragment)
        }
    }
}

