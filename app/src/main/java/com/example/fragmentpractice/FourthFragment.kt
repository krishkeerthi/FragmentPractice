package com.example.fragmentpractice

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.widget.Toast
import androidx.core.app.SharedElementCallback
import androidx.core.util.LogWriter
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.*
import androidx.loader.app.LoaderManager
import com.example.fragmentpractice.databinding.FragmentFourthBinding
import kotlinx.coroutines.flow.combine
import java.io.FileDescriptor
import java.io.PrintWriter
import java.io.StringWriter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

private const val ID_ONE = 1
private const val ID_TWO = 2

/**
 * A simple [Fragment] subclass.
 * Use the [FourthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FourthFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentFourthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFourthBinding.bind(view)

        parentFragmentManager.commit {
            setPrimaryNavigationFragment(this@FourthFragment)
        }
        //parentFragmentManager.beginTransaction().setPrimaryNavigationFragment(this)

        //context menu
        registerForContextMenu(binding.fourthFragTextview)
        unregisterForContextMenu(binding.fourthFragTextview)
        registerForContextMenu(binding.fourthFragTextview)

        //dumpingCheck()

        contextCheck()

    }

    private fun dumpingCheck(){
        val writer = LogWriter("Krish Log")
        dump("Krish Dump", null, PrintWriter(writer), null)
        val dump: String = writer.toString()
        Toast.makeText(requireContext(), "dump $dump", Toast.LENGTH_SHORT).show()
    }
    private fun contextCheck(){
        // Context usage
        context?.let { context ->
            Log.d(TAG, "getContext: ${context.applicationInfo.className}")
            Log.d(TAG, "onViewCreated: ${context.packageCodePath}")
            Log.d(TAG, "onViewCreated: ${context.packageName}")
            Log.d(TAG, "onViewCreated: ${context.packageManager}")
            Log.d(TAG, "onViewCreated: ${context.packageResourcePath}") // same as package code path
            Log.d(TAG, "onViewCreated: ${context.opPackageName}")
            //Return the package name that should be used for android.app.AppOpsManager calls from this context, so that app
            // ops manager's uid verification will work with the name.

            Log.d(TAG, "onViewCreated: ${context.resources.configuration.orientation}")
            Log.d(TAG, "onViewCreated: ${context.resources.configuration.locales}")
            Log.d(TAG, "onViewCreated: ${context.resources.configuration.densityDpi}")
        }

        Log.d(TAG, "user visible hint: ${userVisibleHint}")
    }

    override fun onConfigurationChanged(newConfig: Configuration) { // not called
        Log.d(TAG, "onConfigurationChanged: ")
        super.onConfigurationChanged(newConfig)
        Toast.makeText(requireContext(), "${newConfig.orientation}", Toast.LENGTH_SHORT).show()
        //ORIENTATION_UNDEFINED = 0;
        // ORIENTATION_PORTRAIT = 1;
        // ORIENTATION_LANDSCAPE = 2;
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun getLifecycle(): Lifecycle {
        Log.d(TAG, "getLifecycle: ${super.getLifecycle().currentState.name}")
        return super.getLifecycle()

        //Returns the Lifecycle of the provider.
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)

        //Supply the construction arguments for this fragment
    }

    //interface to global information about an application environment. This is an abstract class whose implementation is
    // provided by the Android system. It allows access to application-specific resources and classes, as well as up-calls for
    // application-level operations such as launching activities, broadcasting and receiving intents, etc.
    override fun getContext(): Context? {

        return super.getContext()
        //Return the Context this fragment is currently associated with.


    }

    override fun setTargetFragment(fragment: Fragment?, requestCode: Int) {
        super.setTargetFragment(fragment, requestCode)

        // Optional target for this fragment. This may be used, for example, if this fragment is being started by another,
    // and when done wants to give a result back to the first
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        // this.isHidden whether fragment is hidden or not

        // by default fragment is not hidden, when hide is called the hidden state
        // is changed and this callback is called
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun getUserVisibleHint(): Boolean {
        return super.getUserVisibleHint() // mUserVisibleHint internal property that is set and get
        //  Hint provided by the app that this fragment is currently visible to the user.

    }

    // menu methods start
    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)  // sets mHasMenu property internally in fragment class
        // In activity owned appbar
        //Report that this fragment would like to participate in populating the options
        // menu by receiving a call to onCreateOptionsMenu and related methods
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible) // sets mMenuVisible property

        //Set a hint for whether this fragment's menu should be visible.
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        // androidx.fragment.app.Fragment Called when a context menu for the view is about to be shown.
    // Unlike onCreateOptionsMenu, this will be called every time the context menu is about to be shown and
    // should be populated for the view

         //call this
        menu.setHeaderTitle("Select The Action")

        menu.add(0, ID_ONE, 0, "Call")//groupId, itemId, order, title
        menu.add(0, ID_TWO, 0, "SMS")
    }

    override fun registerForContextMenu(view: View) { // sets this view needs context menu
        super.registerForContextMenu(view)
        //Registers a context menu to be shown for the given view (multiple views can show the context menu).
        // This method will set the View.OnCreateContextMenuListener on the view to this fragment, when long pressed oncreate context menu will be called
        // so onCreateContextMenu(ContextMenu, View, ContextMenu.ContextMenuInfo) will be called when it is time to show the context menu.
    }

    override fun unregisterForContextMenu(view: View) {
        super.unregisterForContextMenu(view)
        // Prevents a context menu to be shown for the given view. This method will remove the View
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        // super.onContextItemSelected(item) returns false
        // similar to on options item selected

         when(item.itemId){
            ID_ONE -> {
                Toast.makeText(requireContext(), "call clicked", Toast.LENGTH_SHORT).show()
            }
            else ->{
                Toast.makeText(requireContext(), "others clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //Initialize the contents of the Fragment host's standard options menu.
        //you must have first called setHasOptionsMenu.

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        //Prepare the Screen's standard options menu to be displayed. This is called right before the menu is shown,
    // every time it is shown. You can use this method to efficiently enable/disable items or otherwise dynamically modify the contents.

        //call invalidateOptionsMenu() to request that the system call onPrepareOptionsMenu().

    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
        //Called when this fragment's option menu items are no longer being included in the overall options menu.
    // Receiving this call means that the menu needed to be rebuilt, but this fragment's items were not included in the newly built menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        //This hook is called whenever an item in your options menu is selected. The default implementation simply
    // returns false to have the normal processing happen (calling the item's Runnable or sending a message to its Handler as appropriate).

        //boolean Return false to allow normal menu processing to proceed, true to consume it here\

        // two fragments are running on same screen and both fragments implemented onoptionitemselected, in such case
        // returning false will check all fragments for onOptionsItemSelected, if we dont want that then
        // set true so once item selection is handled from one fragment then stop checking other fragments

    }



    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
        //This hook is called whenever the options menu is being closed
    // (either by the user canceling the menu with the back/menu button, or when an item is selected

        // closeOptionsMenu, closeContextMenu belongs to activity not fragment
    }



    // menu methods end


    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.onMultiWindowModeChanged(isInMultiWindowMode)

        //Called when the Fragment's activity changes from fullscreen mode to multi-window mode and visa-versa.
    // This is generally tied to Activity.onMultiWindowModeChanged of the containing Activity.
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
        //Called by the system when the activity changes to and from picture-in-picture mode.
    // This is generally tied to Activity.onPictureInPictureModeChanged of the containing Activity.
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
        //Callback for when the primary navigation state of this Fragment has changed.
    }



    override fun getLoaderManager(): LoaderManager {
        return super.getLoaderManager()
        //loadermanager
        //Used to write apps that run on platforms prior to Android 3.0. When running on Android 3.0 or above,
    // this implementation is still used;

    }

    override fun dump(
        prefix: String,
        fd: FileDescriptor?,
        writer: PrintWriter,
        args: Array<out String>?
    ) {
        super.dump(prefix, fd, writer, args)
        Log.d(TAG, "dump: called")

        //Print the Fragments's state into the given stream.
    }

    // Transition methods
    override fun setEnterTransition(transition: Any?) {
        super.setEnterTransition(transition)
        //Sets the Transition that will be used to move Views into the initial scene
    }

    override fun getEnterTransition(): Any? {
        return super.getEnterTransition()
        //returns mAnimationInfo.mEnterTransition set by set Enter transition
    }

    override fun setReturnTransition(transition: Any?) {
        super.setReturnTransition(transition)
        //Sets the Transition that will be used to move Views out of the scene when the Fragment is preparing
    // to be removed, hidden, or detached because of popping the back stack
        // if not set default is getEnterTransition()
    }

    override fun getReturnTransition(): Any? {
        return super.getReturnTransition()
        // returns mAnimationInfo.mReturnTransition
    }

    override fun setExitTransition(transition: Any?) {
        super.setExitTransition(transition)
        //Sets the Transition that will be used to move Views out of the scene when the fragment is removed, hidden,
    // or detached when not popping the back stack.
    }

    override fun getExitTransition(): Any? {
        return super.getExitTransition()
        // returns mAnimationInfo.mExitTransition

    }

    override fun setReenterTransition(transition: Any?) {
        super.setReenterTransition(transition)
        //Sets the Transition that will be used to move Views in to the scene when returning due to
    // popping a back stack
        // if not set default is getExitTransition()
    }

    override fun getReenterTransition(): Any? {
        return super.getReenterTransition()
        // returns mAnimationInfo.mReenterTransition
    }

    override fun setSharedElementEnterTransition(transition: Any?) {
        super.setSharedElementEnterTransition(transition)
        //Sets the Transition that will be used for shared elements transferred into the content Scene.
        //A null value will cause transferred shared elements to blink to the final position.
    }

    override fun getSharedElementEnterTransition(): Any? {
        return super.getSharedElementEnterTransition()

    }

    override fun setSharedElementReturnTransition(transition: Any?) {
        super.setSharedElementReturnTransition(transition)
        //Sets the Transition that will be used for shared elements transferred back during a pop of the back stack.
        //f no value is set, the default will be to use the same value as setSharedElementEnterTransition(Object).
    }

    override fun getSharedElementReturnTransition(): Any? {
        return super.getSharedElementReturnTransition()
    }

    override fun setAllowEnterTransitionOverlap(allow: Boolean) {
        super.setAllowEnterTransitionOverlap(allow)
        //Sets whether the the exit transition and enter transition overlap or not. When true, the enter transition will start as soon as possible.
    // When false, the enter transition will wait until the exit transition completes before starting.
    }

    override fun getAllowEnterTransitionOverlap(): Boolean {
        return super.getAllowEnterTransitionOverlap()
    }

    override fun setAllowReturnTransitionOverlap(allow: Boolean) {
        super.setAllowReturnTransitionOverlap(allow)
        //Sets whether the the return transition and reenter transition overlap or not. When true, the reenter transition
    // will start as soon as possible. When false, the reenter transition will wait until the return transition completes
    // before starting.
    }

    override fun getAllowReturnTransitionOverlap(): Boolean {
        return super.getAllowReturnTransitionOverlap()
    }

    override fun postponeEnterTransition() {
        super.postponeEnterTransition()
        //Postpone the entering Fragment transition until startPostponedEnterTransition() or
    // FragmentManager.executePendingTransactions() has been called.

        //This method should be called before being added to the FragmentTransaction or in onCreate(Bundle),
    // onAttach(Context), or onCreateView(LayoutInflater, ViewGroup, Bundle)}.

        //startPostponedEnterTransition() must be called to allow the Fragment to start the transitions
    }

    override fun startPostponedEnterTransition() {
        super.startPostponedEnterTransition()

        //Begin postponed transitions after postponeEnterTransition() was called. If postponeEnterTransition() was called,
    // you must call startPostponedEnterTransition() or FragmentManager.executePendingTransactions() to complete the FragmentTransaction
    }

    // end of transition methods

    // Inflation related methods

    // layoutinflater - Instantiates a layout XML file into its corresponding View objects
    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(savedInstanceState)
        //Returns the LayoutInflater used to inflate Views of this Fragment.
    }

//    @SuppressLint("RestrictedApi")
//    override fun getLayoutInflater(savedFragmentState: Bundle?): LayoutInflater {
//        return super.getLayoutInflater(savedFragmentState)
//    }
    // deprecated use ongetlayoutinflater()

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Log.d(TAG, "onInflate: ")
        //Called when a fragment is being created as part of a view layout inflation,
        //context – The Activity that is inflating this fragment.
        //attrs – The attributes at the tag where the fragment is being created.
        //savedInstanceState – If the fragment is being re-created from a previous saved state, this is the state.
    }

    // inflation related methods ends

    // viewmodel, viewmodel lifecycle related
    override fun getViewModelStore(): ViewModelStore {
        return super.getViewModelStore()

        //An instance of ViewModelStore must be retained through configuration changes:
    // if an owner of this ViewModelStore is destroyed and recreated due to configuration changes,
    // new instance of an owner should still have the same old instance of ViewModelStore.
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        return super.getDefaultViewModelProviderFactory()
    }

    override fun getViewLifecycleOwner(): LifecycleOwner {
        return super.getViewLifecycleOwner()
        //Get a LifecycleOwner that represents the Fragment's View lifecycle.
    // In most cases, this mirrors the lifecycle of the Fragment itself, but in cases of detached Fragments,
    // the lifecycle of the Fragment can be considerably longer than the lifecycle of the View itself.
    }

    override fun getViewLifecycleOwnerLiveData(): LiveData<LifecycleOwner> {
        return super.getViewLifecycleOwnerLiveData()
        //Retrieve a LiveData which allows you to observe the lifecycle of the Fragment's View.
        //This will be set to the new LifecycleOwner after onCreateView returns a non-null View and will set to null after onDestroyView().
    }

    // start activity methods

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
    }

    override fun startIntentSenderForResult(
        intent: IntentSender?,
        requestCode: Int,
        fillInIntent: Intent?,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle?
    ) {
        super.startIntentSenderForResult(
            intent,
            requestCode,
            fillInIntent,
            flagsMask,
            flagsValues,
            extraFlags,
            options
        )

        // use
        //Use registerForActivityResult(ActivityResultContract, ActivityResultCallback) passing in a
    // androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult object for the ActivityResultContract.
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        //Callback for the result from requesting permissions. This method is invoked for every call on requestPermissions(String[], int).

        //use
        //Use registerForActivityResult(ActivityResultContract, ActivityResultCallback) passing in a
    // androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions object for
    // the ActivityResultContract and handling the result in the callback.

    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return super.shouldShowRequestPermissionRationale(permission)
        //Gets whether you should show UI with rationale before requesting a permission.
        //Explain why your app needs the permission
    }

    override fun setInitialSavedState(state: SavedState?) {
        super.setInitialSavedState(state)
        Log.d(TAG, "setInitialSavedState: ")
        //Set the initial saved state that this Fragment should restore itself from when first being constructed,
    }

    override fun setRetainInstance(retain: Boolean) {
        super.setRetainInstance(retain)
        //Control whether a fragment instance is retained across Activity re-creation (such as from a configuration change).
    // If set, the fragment lifecycle will be slightly different when an activity is recreated:
        //onDestroy() will not be called (but onDetach() still will be, because the fragment is being detached from its current activity).
        //onCreate(Bundle) will not be called since the fragment is not being re-created.
        //onAttach(Activity) and onActivityCreated(Bundle) will still be called.

        // use
        // Instead of retaining the Fragment itself, use a non-retained Fragment and keep retained state in a
    // ViewModel attached to that Fragment. The ViewModel's constructor and its onCleared() callback provide the signal
    // for initial creation and final destruction of the retained state

    }
    

    override fun setEnterSharedElementCallback(callback: SharedElementCallback?) {
        super.setEnterSharedElementCallback(callback)
        //When custom transitions are used with Fragments, the enter transition callback is called when
    // this Fragment is attached or detached when not popping the back stack.
    }

    override fun setExitSharedElementCallback(callback: SharedElementCallback?) {
        super.setExitSharedElementCallback(callback)
        //When custom transitions are used with Fragments, the exit transition callback is called when this Fragment
    // is attached or detached when popping the back stack.

        //callback – Used to manipulate the shared element transitions on this Fragment
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FourthFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
             FourthFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1) // stores in mMap  ArrayMap<String, Object>
                    putString(ARG_PARAM2, param2)
                }
            }

//            val fragment= FourthFragment()
//            val bundle = Bundle()
//            bundle.putString(ARG_PARAM1, param1)
//            bundle.putString(ARG_PARAM2, param2)
//
//            fragment.arguments = bundle
        // setArguments(bundle) the above is similar
//
//        }
    }
}