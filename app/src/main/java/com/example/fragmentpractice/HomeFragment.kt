package com.example.fragmentpractice

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import com.example.fragmentpractice.databinding.FragmentHomeBinding

class HomeFragment : Fragment() { // detaches and attaches during conf. changes, state not restored by default

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: TestViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: Fragment Lifecycle1")
    }

    override fun onAttach(activity: Activity) {  // deprecated so onAttach(context) used
        super.onAttach(activity)
        Log.d(TAG, "onAttach: Fragment Lifecycle1")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //retainInstance = false // instead of retaining fragment, use viewmodel to retain states
        Log.d(TAG, "onCreate: Fragment Lifecycle1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Fragment Lifecycle1")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Fragment Lifecycle1")
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        // using bundle value
        val data = requireArguments().getString("text_value")
        binding.textView.text = data

        // using viewmodel value, scoped to host activity
        binding.homeTextView.text = viewModel.currentValue.toString()
        viewModel.currentValue = 2

        // Toolbar menu inflation
        binding.homeToolbar.inflateMenu(R.menu.home_menu)

        // modify menu
        binding.menuReverseButton.setOnClickListener {
            binding.homeToolbar.menu.clear() // clear menu before modifying
            // else existing menu items are not modified or removed. new items will be
            // added together

            binding.homeToolbar.inflateMenu(R.menu.home_reversed_menu)
        }

        //setHasOptionsMenu()

        binding.homeNextButton.setOnClickListener {

            // requireActivity().supportFragmentManager and parentFragmentManager behaves similarly

//            requireActivity().supportFragmentManager.commit {  androidx.fragment.app.FragmentManager
//                //FragmentManager for interacting with fragments associated with this activity.
//                setCustomAnimations( R.anim.slide_in,
//                    R.anim.fade_out,
//                    R.anim.fade_in, // pop
//                    R.anim.slide_out) // pop
//
//                setReorderingAllowed(true)
//                replace<SecondFragment>(R.id.fragmentContainerView)
//                addToBackStack("second fragment")
//            }
            parentFragmentManager.commit { // androidx.fragment.app.FragmentManager
                // FragmentManager for interacting with fragments associated with this
                // fragment's activity
                setCustomAnimations( R.anim.slide_in, //enter
                    R.anim.fade_out, //enter
                    R.anim.fade_in, // pop
                    R.anim.slide_out) // pop

                setReorderingAllowed(true)
                replace<SecondFragment>(R.id.fragmentContainerView)
                addToBackStack("second fragment")
                // hide(this@HomeFragment) this method
            }


            //requireActivity().fragmentManager
                //  android.app.FragmentManager

            //childFragmentManager
            // private FragmentManager for placing and managing Fragments inside of this Fragment.


            // commitNow() does not work well with addToBackStack()
            // commitNow() causes This transaction is already being added to the back stack
            //parentFragmentManager.executePendingTransactions() handles back stack issues
        }


    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        // we are getting savedinstancestate here from on save instance state callback, we use it restore the state
        Log.d(TAG, "onViewStateRestored: Fragment Lifecycle1")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Fragment Lifecycle1")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Fragment Lifecycle1")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Fragment Lifecycle1")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Fragment Lifecycle1")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: Fragment Lifecycle1")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Fragment Lifecycle1")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach: Fragment Lifecycle1")
        super.onDetach()
    }
}