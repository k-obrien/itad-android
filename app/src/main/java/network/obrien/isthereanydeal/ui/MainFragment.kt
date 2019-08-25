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

package network.obrien.isthereanydeal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_main.*
import network.obrien.isthereanydeal.R

class MainFragment : DaggerFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabs.setupWithViewPager(view_pager)
        view_pager.adapter = SectionsPagerAdapter(requireFragmentManager())
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int) =
            MainActivity.PlaceholderFragment.newInstance(position + 1)

        override fun getPageTitle(position: Int): CharSequence? = "Tab ${position + 1}"

        override fun getCount() = 3
    }
}
