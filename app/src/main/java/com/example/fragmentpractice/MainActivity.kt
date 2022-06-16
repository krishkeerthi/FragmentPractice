package com.example.fragmentpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.fragmentpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null)  // don't redraw during conf. changes
        addFragment(bundleOf("text_value" to  "Hello from main activity"))

    }

    private fun addFragment(bundle: Bundle){
        supportFragmentManager.commit{  // without adding fragment dependency commit is unknown
            setReorderingAllowed(true)  // optimize the fragments state during this transaction
            add<HomeFragment>(R.id.fragmentContainerView, args = bundle)
        }

        Toast.makeText(this, "Fragment added", Toast.LENGTH_SHORT).show()
    }
}