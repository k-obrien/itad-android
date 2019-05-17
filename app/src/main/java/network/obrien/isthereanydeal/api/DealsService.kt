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

package network.obrien.isthereanydeal.api

import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Deferred
import network.obrien.isthereanydeal.vo.Deal
import retrofit2.http.GET
import retrofit2.http.Query

interface DealsService {
    @GET("v01/deals/list/")
    fun getDeals(
        @Query("key")
        key: String,

        @Query("offset")
        offset: Int = 0,

        @Query("limit")
        limit: Int = 20,

        @Query("region")
        region: String? = null,

        @Query("country")
        country: String? = null,

        @Query("shops")
        shops: String? = null
    ): Deferred<ApiResponse<Meta, Data>>

    data class Meta(
        @SerializedName("currency")
        val currency: String
    )

    data class Data(
        @SerializedName("count")
        val count: Long,

        @SerializedName("list")
        val deals: List<Deal>
    )
}
