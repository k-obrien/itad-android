/*
 * Copyright (C) 2018-present Kieran O'Brien
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

package network.obrien.isthereanydeal.ui.settings

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
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
        (intent?.extras?.getString(INTENT_EXTRA_KEY_DEPENDENCY))?.also { title = it }

        webview.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                supportActionBar?.subtitle = webview.title
            }
        }

        intent?.extras?.getString(INTENT_EXTRA_KEY_LICENSE)?.also {
            webview.loadUrl(getString(R.string.preference_license_base_url, it))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }

        else -> super.onOptionsItemSelected(item)
    }
}
