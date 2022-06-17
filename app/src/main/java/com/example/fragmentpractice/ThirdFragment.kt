package com.example.fragmentpractice

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import com.example.fragmentpractice.databinding.FragmentThirdBinding

//Only one FragmentManager is allowed to control the fragment back stack at any given time
class ThirdFragment : Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView: Fragment Lifecycle3")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Fragment Lifecycle3")
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentThirdBinding.bind(view)

        // fragment result api listener
        setFragmentResultListener("communication_key"){ requestKey, bundle ->
            binding.thirdTextView.text = bundle.getInt("numeric_value").toString()

        }

        // Menu inflation
        val toolbar = binding.toolbar.root
        toolbar.inflateMenu(R.menu.third_fragment_menu)


        // handling menu clicks
        toolbar.setOnMenuItemClickListener{ item ->
            when(item.itemId){
                R.id.third_fragment_search -> {
                    searchOperation()
                    true // it says the click is performed successfully
                }
                R.id.third_fragment_edit -> {
                    editOperation()
                    true
                }
                else -> false
                }
            }

        // setting toolbar navigation
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }


        // add base fragment
        addBaseFragment()

        binding.fragment3HomeButton.setOnClickListener {
            addBaseFragment()
        }

        binding.fragment3Frag2Button.setOnClickListener {
            childFragmentManager.commit {
                setReorderingAllowed(true)
                replace<SecondFragment>(R.id.fragment3ContainerView)

            }
        }

        binding.fragment3Frag3Button.setOnClickListener {
            childFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ThirdFragment>(R.id.fragment3ContainerView)
            }
        }

//        val fragmentTransaction = parentFragmentManager.beginTransaction()
//       // fragmentTransaction.hide(this)  show, hide, attach, detach not working
//        fragmentTransaction.detach(this)
    }

    private fun addBaseFragment() {
        childFragmentManager.commit{  // using parent fragment manager the same view is created on every click
                                    // because addBaseFragment() is called in onviewcreated
            setReorderingAllowed(true)
            replace<HomeFragment>(R.id.fragment3ContainerView, args =
            bundleOf("text_value" to  "Home fragment called from third fragment"))
            setPrimaryNavigationFragment(parentFragmentManager.primaryNavigationFragment)
        }
    }

    private fun editOperation() {
        val toolbar = binding.toolbar.root

        val editItem = toolbar.menu.findItem(R.id.third_fragment_edit)
        editItem.isVisible = false
    }

    private fun searchOperation() {
        Toast.makeText(requireContext(), "Search is clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach: Fragment Lifecycle3")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: Fragment Lifecycle3")



        val transitionInflater = TransitionInflater.from(requireContext())
        enterTransition= transitionInflater.inflateTransition(R.transition.slide_right)
       // exitTransition = transitionInflater.inflateTransition(R.transition.slide_left)
        // during exit slide_left opposite of slide_right is automatically performed
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewStateRestored: Fragment Lifecycle3")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart: Fragment Lifecycle3")
        super.onStart()

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        // fragmentTransaction.hide(this)
        fragmentTransaction.detach(this)
    }

    override fun onResume() {
        Log.d(TAG, "onResume: Fragment Lifecycle3")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause: Fragment Lifecycle3")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop: Fragment Lifecycle3")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: Fragment Lifecycle3")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy: Fragment Lifecycle3")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(TAG, "onDetach: Fragment Lifecycle3")
        super.onDetach()
    }
}