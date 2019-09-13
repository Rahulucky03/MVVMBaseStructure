package com.taurus.modernandroiddevelopmentkata.core.navigation

import android.content.Context
import com.taurus.modernandroiddevelopmentkata.core.routing.BaseController
import com.taurus.modernandroiddevelopmentkata.core.routing.BaseRouteManager
import javax.inject.Inject

class NavigationController @Inject constructor
(val context: Context, private val baseRouteManager: BaseRouteManager)
    : Navigator, BaseController by baseRouteManager
