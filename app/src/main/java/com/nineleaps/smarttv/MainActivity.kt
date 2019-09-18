package com.nineleaps.smarttv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        loadFragment(ImageSliderFragment())
    }

    /**
     * support function for fragment transaction
     */
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
    /**
     * load fragment to fragment container
     */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, fragment)
        }
    }

}
