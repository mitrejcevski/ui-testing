package nl.jovmit.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import nl.jovmit.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setTitle(R.string.hello)
    }
}