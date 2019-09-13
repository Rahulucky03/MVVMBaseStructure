package com.taurus.modernandroiddevelopmentkata.core

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.taurus.modernandroiddevelopmentkata.core.di.viewmodel.ViewModelFactoryM
import com.taurus.modernandroiddevelopmentkata.core.locale.LocaleManager
import com.taurus.modernandroiddevelopmentkata.core.navigation.NavigationController
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VM : ViewModel>(@LayoutRes private val layoutResId: Int,
                                            private val viewModelClass: Class<VM>
        ) : DaggerAppCompatActivity(), BaseFragment.Callback {

    var allowTransaction = true

    @Inject
    lateinit var navigationController: NavigationController

    @Inject
    protected lateinit var viewModelFactoryM: ViewModelFactoryM<VM>

    protected lateinit var viewModel: VM
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)

        viewModel = ViewModelProviders.of(this, viewModelFactoryM).get(viewModelClass)
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun onTransitionStarted() {
        allowTransaction = false
    }

    override fun onTransitionEnded() {
        allowTransaction = true
    }

    override fun onTransitionRepeated() {
        allowTransaction = false
    }

    override fun onResume() {
        super.onResume()
        allowTransaction = true
    }

    override fun onStop() {
        super.onStop()

        allowTransaction = false
    }

    fun isAllowTransition(): Boolean {
        return allowTransaction
    }

    fun isNetworkConnected(): Boolean = isNetworkConnected(applicationContext)

    fun hideKeyboard() {
        val view: View? = this.currentFocus
        val inputMethodManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    fun showShortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun showNotificationDialog() {
        //navigationController.launchNotificationDialogFragment(this)
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}
