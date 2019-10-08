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

package network.obrien.isthereanydeal.data.region.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegionCurrency(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "sign") val symbol: String,
    @field:Json(name = "html") val htmlEncodedSymbol: String,
    @field:Json(name = "delimiter") val delimiter: String,
    @field:Json(name = "left") val leftAligned: Boolean
)
