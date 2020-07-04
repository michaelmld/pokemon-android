package com.example.pokemon

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

//Implement special dagger interface so we don't have to explicitly call inject
class MainActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var myExample: MyExample


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Old Dagger
        //This is saying, dagger please get all the dependencies for MainActivity i.e myExample
        //(application as BaseApplication).component.inject(this)
        myExample.test()


//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }


    //https://my-json-server.typicode.com/typicode/demo/comments

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
