package com.example.fragmentpractice

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {
    // oncreateview, onviewcreated never called.
    // onviewcreated called only when oncreateview overriden

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {  // returns subclass of dialog
                                                                // eg(alert dialog, date, time picker dialog)
        Log.d(TAG, "onCreateDialog: Fragment Lifecycle dialog")
        val customView = requireActivity().layoutInflater.inflate(R.layout.custom_view, null)
        return AlertDialog.Builder(requireContext())
            .setView(customView) // sets custom view
            .setTitle("Custom Dialog")
            .setMessage("Custom dialog message :)")
            .setCancelable(true)
            .setPositiveButton("Yes"){ dialogInterface, which ->
//                dialog 	DialogInterface!: the dialog that received the click
//                which 	Int: the button that was clicked (ex. DialogInterface#BUTTON_POSITIVE) or the position of the item clicked
                //dialogInterface.dismiss()  // dialog dismiss by default( onDismiss())
                Toast.makeText(requireContext(), "Which value is $which", Toast.LENGTH_SHORT).show()
            }
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        Log.d(TAG, "onCancel: Fragment Lifecycle dialog")
        super.onCancel(dialog)
    }

    override fun onDismiss(dialog: DialogInterface) {
        Log.d(TAG, "onDismiss: Fragment Lifecycle dialog")
        super.onDismiss(dialog)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(ContentValues.TAG, "onAttach: Fragment Lifecycle dialog")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(ContentValues.TAG, "onCreate: Fragment Lifecycle dialog")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        Log.d(ContentValues.TAG, "onViewStateRestored: Fragment Lifecycle dialog")
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onStart() {
        Log.d(ContentValues.TAG, "onStart: Fragment Lifecycle dialog")
        super.onStart()

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        // fragmentTransaction.hide(this)
        fragmentTransaction.detach(this)
    }

    override fun onResume() {
        Log.d(ContentValues.TAG, "onResume: Fragment Lifecycle dialog")
        super.onResume()
    }

    override fun onPause() {
        Log.d(ContentValues.TAG, "onPause: Fragment Lifecycle dialog")
        super.onPause()
    }

    override fun onStop() {
        Log.d(ContentValues.TAG, "onStop: Fragment Lifecycle dialog")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(ContentValues.TAG, "onDestroyView: Fragment Lifecycle dialog")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(ContentValues.TAG, "onDestroy: Fragment Lifecycle dialog")
        super.onDestroy()
    }

    override fun onDetach() {
        Log.d(ContentValues.TAG, "onDetach: Fragment Lifecycle dialog")
        super.onDetach()
    }

    companion object{
        const val TAG = "Custom dialog tag"
    }
}