package com.example.articals.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.articals.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            addArticalListFragment()
        }
    }


    fun addArticalListFragment() {
        this.supportFragmentManager.beginTransaction()
            .add(R.id.container, ArticalListFragment.newInstance()).commit()
    }
}