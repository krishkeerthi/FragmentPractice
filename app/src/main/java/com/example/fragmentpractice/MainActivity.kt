package com.example.fragmentpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.Lifecycle
import com.example.fragmentpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: TestViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.currentValue = 1


        if(savedInstanceState == null)  // don't redraw during conf. changes
        addFragment(bundleOf("text_value" to  "Hello from main activity"))
    }

    private fun addFragment(bundle: Bundle){
        supportFragmentManager.commit{  // without adding fragment dependency commit is unknown
            setReorderingAllowed(true)  // optimize the fragments state during this transaction
            add<HomeFragment>(R.id.fragmentContainerView, args = bundle)
            addToBackStack("home fragment")  // adding root frag. to back stack,
                                                   // during pop up root frag got detached and
                                                    // empty frag container is shown
        }

        Toast.makeText(this, "Fragment added", Toast.LENGTH_SHORT).show()
    }
}