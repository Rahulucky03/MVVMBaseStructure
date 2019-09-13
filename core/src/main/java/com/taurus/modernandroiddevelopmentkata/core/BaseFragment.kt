package com.taurus.modernandroiddevelopmentkata.core

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.taurus.modernandroiddevelopmentkata.core.di.viewmodel.ViewModelFactoryM
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationController
import com.taurus.modernandroiddevelopmentkata.core.toolbar.FragmentToolbar
import com.taurus.modernandroiddevelopmentkata.core.toolbar.ToolbarManager
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

    private var mActivity: BaseActivity<*>? = null
    private var mViewModel: VM? = null
    protected var root: ViewGroup? = null
    var isFragmentLoaded = false
    var toolbarManager: ToolbarManager? = null

    @Inject
    lateinit var navigationController: NavigationController

    open fun onKeyboardOpen() {

    }

    open fun onKeyboardClose() {

    }

    @Inject
    protected lateinit var viewModelFactoryM: ViewModelFactoryM<VM>

    /**
     * ViewModel instance that provided by ViewModelProvider
     */
    //private lateinit var stateMachine: VM

    /**
     * NavController for using Navigation Arch Component
     */
    private lateinit var navController: NavController


    abstract fun obtainViewModel(): Class<VM>

    @LayoutRes
    abstract fun layoutResId(): Int

    override fun onAttach(context: Context) {
        try {
            super.onAttach(context)
            if (context is BaseActivity<*>) {
                val activity = context as BaseActivity<*>?
                this.mActivity = activity
                activity!!.onFragmentAttached()
            }
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement FragmentListener")
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this, viewModelFactoryM).get(obtainViewModel())
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?) =
        inflater.inflate(layoutResId(), container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        try {
            val toolbarManager = ToolbarManager(toolbarBuilder(), view, navController)
            toolbarManager.prepareToolbar()
            isFragmentLoaded = true
            mViewModel?.let {
                onReadyToRender(view, it)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onStop() {
        //toolbarManager?.stopCountDownTimer()
        super.onStop()
    }

    override fun onDetach() {
        mActivity = null
        super.onDetach()
    }

    override fun onDestroy() {
        super.onDestroy()

        isFragmentLoaded = false
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        var animation: Animation? = super.onCreateAnimation(transit, enter, nextAnim)

        if (animation == null && nextAnim != 0) {
            animation = AnimationUtils.loadAnimation(activity, nextAnim)
        }

        if (animation != null) {
            view!!.setLayerType(View.LAYER_TYPE_HARDWARE, null)

            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {
                    mActivity?.onTransitionStarted()
                }

                override fun onAnimationEnd(animation: Animation) {
                    if (view != null) {
                        view!!.setLayerType(View.LAYER_TYPE_NONE, null)
                    }
                    mActivity?.onTransitionEnded()
                }

                override fun onAnimationRepeat(animation: Animation) {
                    mActivity?.onTransitionRepeated()
                }
                // ...other AnimationListener methods go here...
            })
        }
        return animation
    }

    /**
     * @return the identifying enum for the current fragment.
     */
    abstract fun getFragmentTAG(): FragmentTAG

    /**
     * An enumerator of TAGs for Fragments.
     */
    enum class FragmentTAG {
        SIGN_IN_FRAG, SIGN_UP_FRAG, FORGOT_PASSWORD_FRAG
    }

    fun getBaseActivity(): BaseActivity<*>? {
        return mActivity
    }


    fun isNetworkConnected(): Boolean {
        return mActivity != null && mActivity!!.isNetworkConnected()
    }

    fun hideKeyboard() {
        if (mActivity != null) {
            mActivity!!.hideKeyboard()
        }
    }

    fun showShortToast(message: String) {
        if (mActivity != null) {
            mActivity!!.showShortToast(message)
        }
    }

    fun showNotificationDialog() {
        if (mActivity != null) {
            mActivity!!.showNotificationDialog()
        }
    }

    fun isAllowTransition(): Boolean {
        return if (mActivity != null) {
            mActivity!!.isAllowTransition()
        } else {
            false
        }
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)

        fun onTransitionStarted()

        fun onTransitionEnded()

        fun onTransitionRepeated()
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract fun getViewModel(): VM


    protected abstract fun toolbarBuilder(): FragmentToolbar

    protected abstract fun onReadyToRender(view: View, stateMachine: VM)

}
