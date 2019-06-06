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

package network.obrien.isthereanydeal.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigDecimal

interface DealsService {
    @GET("v01/deals/list/")
    suspend fun getDeals(
        @Query("key") apiKey: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("region") region: String? = null,
        @Query("country") country: String? = null,
        @Query("shops") stores: String? = null
    ): ApiResponse<Meta, Data>

    data class Meta(@SerializedName("currency") val currency: String)

    data class Data(
        @SerializedName("count") val count: Long,
        @SerializedName("list") val deals: List<Deal>
    )

    data class Deal(
        @SerializedName("plain") val gameId: String,
        @SerializedName("title") val gameTitle: String,
        @SerializedName("price_new") val currentPrice: BigDecimal,
        @SerializedName("price_old") val previousPrice: BigDecimal,
        @SerializedName("price_cut") val discountPercent: Int,
        @SerializedName("added") val timeAddedSecondsUtc: Long,
        @SerializedName("shop") val store: Store,
        @SerializedName("drm") val drm: List<String>,
        @SerializedName("urls") val links: Links
    )

    data class Store(
        @SerializedName("id") val id: String,
        @SerializedName("name") val name: String
    )

    data class Links(
        @SerializedName("buy") val purchase: String,
        @SerializedName("game") val info: String
    )
}
