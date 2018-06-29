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

package network.obrien.isthereanydeal.license

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_license.*
import network.obrien.isthereanydeal.R

const val INTENT_EXTRA_KEY_DEPENDENCY = "network.obrien.isthereanydeal.intent.extra.KEY_DEPENDENCY"
const val INTENT_EXTRA_KEY_LICENSE = "network.obrien.isthereanydeal.intent.extra.KEY_LICENSE"

class LicenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_license)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.extras[INTENT_EXTRA_KEY_DEPENDENCY] as String

        webview
            .apply {
                settings.loadWithOverviewMode = true
                setInitialScale(170)
                settings.setSupportZoom(true)
                settings.builtInZoomControls = true
                settings.displayZoomControls = false
                webChromeClient = object : WebChromeClient() {
                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        super.onReceivedTitle(view, title)
                        supportActionBar?.subtitle = webview.title
                    }
                }

                loadUrl(getString(R.string.url_licenses, intent.extras[INTENT_EXTRA_KEY_LICENSE]))
            }

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
}
