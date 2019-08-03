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

package network.obrien.isthereanydeal.data.deals.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class Deal(
    @SerializedName("plain") val gameId: String,
    @SerializedName("title") val gameTitle: String,
    @SerializedName("price_new") val currentPrice: BigDecimal,
    @SerializedName("price_old") val previousPrice: BigDecimal,
    @SerializedName("price_cut") val discountPercent: Int,
    @SerializedName("added") val timeAddedSecondsUtc: Long,
    @SerializedName("shop") val store: DealStore,
    @SerializedName("drm") val drm: List<String>,
    @SerializedName("urls") val links: DealLinks
)
