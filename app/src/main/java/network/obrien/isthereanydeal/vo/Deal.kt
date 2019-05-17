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

package network.obrien.isthereanydeal.vo

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Deal(
    @SerializedName("plain")
    val plain: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("price_new")
    val currentPrice: BigDecimal,

    @SerializedName("price_old")
    val previousPrice: BigDecimal,

    @SerializedName("price_cut")
    val discountPercent: Int,

    @SerializedName("added")
    val timeAdded: Long,

    @SerializedName("shop")
    val store: Store,

    @SerializedName("drm")
    val drm: List<String>,

    @SerializedName("urls")
    val links: Links
)

data class Store(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String
)

data class Links(
    @SerializedName("buy")
    val purchase: String,

    @SerializedName("game")
    val info: String
)
