package com.example.pokemon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    //by == provides?
    //https://kotlinlang.org/docs/reference/delegated-properties.html
    //reminds me of property getter/setting from ios...but it can be defined once for multple places
    private val args: SecondFragmentArgs by navArgs()



    //https://stackoverflow.com/questions/25119090/difference-between-oncreateview-and-onviewcreated-in-fragment
    //You should inflate your layout in onCreateView but shouldn't initialize other views using findViewById in onCreateView.
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    //called right after onCreateView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //when you click the previous button go to the first page
        view.findViewById<Button>(R.id.previous_button).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val count = args.myArg
        val countText = getString(R.string.random_heading, count)
        view.findViewById<TextView>(R.id.textview_header).text = countText

        val random = java.util.Random()
        var randomNumber = 0
        if (count > 0) {
            randomNumber = random.nextInt(count + 1)
        }
        view.findViewById<TextView>(R.id.textview_random).text = randomNumber.toString()


    }
}
