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

package network.obrien.isthereanydeal.data.deal.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import network.obrien.isthereanydeal.data.store.model.Store
import network.obrien.isthereanydeal.util.Price

typealias DrmPlatforms = List<String>

@JsonClass(generateAdapter = true)
data class Deal(
    @field:Json(name = "plain") val gameId: String,
    @field:Json(name = "title") val gameTitle: String,
    @field:Json(name = "price_new") @Price val dealPrice: Long,
    @field:Json(name = "price_old") @Price val regularPrice: Long,
    @field:Json(name = "price_cut") val discountPercent: Int,
    @field:Json(name = "added") val timeAddedSecondsUtc: Long,
    @field:Json(name = "shop") val store: Store,
    @field:Json(name = "drm") val drm: DrmPlatforms,
    @field:Json(name = "urls") val links: DealLinks
)
