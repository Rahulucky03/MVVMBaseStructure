package com.taurus.modernandroiddevelopmentkata.core.routing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.taurus.modernandroiddevelopmentkata.core.BaseActivity
import com.taurus.modernandroiddevelopmentkata.core.BaseFragment
import com.taurus.modernandroiddevelopmentkata.core.R
import com.taurus.modernandroiddevelopmentkata.core.utilities.FragmentUtils
import javax.inject.Inject

class BaseRouteManager
@Inject
constructor
(val context: Context) : BaseController {

    override fun popBackStack(context: Context?) {
        val activity = context as BaseActivity<*>
        if (activity.isAllowTransition()) {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun replaceFragment(context: Context?, fragment: BaseFragment<ViewModel>, containerId: Int,
                        isAddToBackStack: Boolean = false, withAnimation: Boolean = true) {
        val activity = context as BaseActivity<*>
        if (!activity.isAllowTransition()) {
            return
        }

        val transaction = activity.supportFragmentManager.beginTransaction()
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.getFragmentTAG().name)
        }

        if (withAnimation) {
            //transaction?.setCustomAnimations(R.anim.trans_left_in, R.anim.trans_left_out, R.anim.trans_right_in, R.anim.trans_right_out)
            transaction.setCustomAnimations(R.anim.fragment_trans_left_in, R.anim.fragment_trans_left_out,
                R.anim.trans_left_in, R.anim.trans_left_out)
        }
        transaction.replace(containerId, fragment, fragment.getFragmentTAG().name)
        transaction.commit()
    }

    fun removeAllFragmentBackStack() {
        val activity = context as AppCompatActivity
        val fragmentManager = activity.supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            FragmentUtils.isDisableFragmentAnimations = true
            activity.supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            FragmentUtils.isDisableFragmentAnimations = false
        }
    }
}