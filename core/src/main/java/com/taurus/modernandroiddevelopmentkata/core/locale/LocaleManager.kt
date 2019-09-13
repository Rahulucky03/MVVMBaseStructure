package com.taurus.modernandroiddevelopmentkata.core.locale

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.preference.PreferenceManager

import java.util.Locale

import android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
import android.os.Build.VERSION_CODES.N

class LocaleManager(context: Context) {

    companion object {

        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_SPANISH = "es"
        const val LANGUAGE_GERMAN = "ge"
        const val LANGUAGE_PORTUGUESE = "po"

        private const val LANGUAGE_KEY = "language_key"

        fun getLocale(res: Resources): Locale {
            val config = res.configuration
            return if (Utility.isAtLeastVersion(N))
                config.locales.get(0)
            else
                config.locale
        }
    }
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    private val language: String?
        get() = prefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH)

    fun setLocale(c: Context): Context {
        return updateResources(c, language)
    }

    fun setNewLocale(c: Context, language: String): Context {
        persistLanguage(language)
        return updateResources(c, language)
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(language: String) {
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context, language: String?): Context {
        var newContext = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = newContext.resources
        val config = Configuration(res.configuration)
        if (Utility.isAtLeastVersion(JELLY_BEAN_MR1)) {
            config.setLocale(locale)
            newContext = newContext.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return newContext
    }
}