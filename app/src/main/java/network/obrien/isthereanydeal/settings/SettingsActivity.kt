/*
 * ITAD Android
 * Copyright (C) 2018-present  Kieran O'Brien
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package network.obrien.isthereanydeal.settings

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.PreferenceScreen
import android.view.MenuItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_settings.*
import network.obrien.isthereanydeal.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if ((fragment_settings as? SettingsFragment)?.onBackPressed() == true) {
            supportActionBar?.title = getString(R.string.title_activity_settings)
            return
        }

        super.onBackPressed()
    }

    class SettingsFragment : PreferenceFragmentCompat(),
        PreferenceFragmentCompat.OnPreferenceStartScreenCallback {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            addPreferencesFromResource(R.xml.preferences)
        }

        override fun onPreferenceStartScreen(
            caller: PreferenceFragmentCompat?,
            preferenceScreen: PreferenceScreen?
        ): Boolean {
            preferenceScreen
                ?.takeIf { (it.title == getString(R.string.title_preference_licenses)) && (it.preferenceCount == 1) }
                ?.apply {
                    removeAll()
                    resources.assets.open("licenses/third_party_licenses.json")
                        .use { inputStream ->
                            inputStream
                                .bufferedReader()
                                .use { it.readText() }
                                .let {
                                    Gson().fromJson<List<ThirdPartyLicenses>>(
                                        it,
                                        object : TypeToken<List<ThirdPartyLicenses>>() {}.type
                                    )
                                }
                                .flatMap { licenses ->
                                    licenses.dependencies.map { dependency ->
                                        Intent()
                                            .apply {
                                                `package` = getString(R.string.app_id)
                                                setClass(context, LicenseActivity::class.java)
                                                putExtra(INTENT_EXTRA_KEY_DEPENDENCY, dependency)
                                                putExtra(INTENT_EXTRA_KEY_LICENSE, licenses.license)
                                            }
                                            .let {
                                                Preference(context).apply {
                                                    title = dependency
                                                    intent = it
                                                }
                                            }
                                    }
                                }
                                .sortedBy { it.title.toString().toLowerCase() }
                                .forEach { addPreference(it) }
                        }
                }

            return navigateToPreferenceScreen(preferenceScreen)
        }

        override fun getCallbackFragment() = this

        fun onBackPressed() =
            navigateToPreferenceScreen((preferenceScreen?.parent as? PreferenceScreen))

        private fun navigateToPreferenceScreen(preferenceScreen: PreferenceScreen?): Boolean {
            preferenceScreen
                ?.let {
                    this.preferenceScreen = it
                    (activity as? SettingsActivity)?.supportActionBar?.title = it.title
                    return true
                }

            return false
        }

        data class ThirdPartyLicenses(
            val dependencies: List<String>,
            val license: String
        )
    }
}

