package com.example.fragmentpractice

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.fragmentpractice.databinding.FragmentHomeBinding
import com.example.fragmentpractice.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: Fragment Lifecycle2")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Fragment Lifecycle2")
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view)

        binding.secondNextButton.setOnClickListener {
            parentFragmentManager.commit {
                add<ThirdFragment>(R.id.fragmentContainerView)
                setReorderingAllowed(true)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: Fragment Lifecycle2")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Fragment Lifecycle2")
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewStateRestored: Fragment Lifecycle2")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Fragment Lifecycle2")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Fragment Lifecycle2")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Fragment Lifecycle2")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Fragment Lifecycle2")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: Fragment Lifecycle2")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Fragment Lifecycle2")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach: Fragment Lifecycle2")
        super.onDetach()
    }
}