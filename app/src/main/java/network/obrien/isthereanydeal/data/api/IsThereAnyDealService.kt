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

package network.obrien.isthereanydeal.data.api

import com.google.gson.annotations.SerializedName
import network.obrien.isthereanydeal.data.deals.model.DealData
import network.obrien.isthereanydeal.data.deals.model.DealMeta
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * IsThereAnyDeal API
 *
 * https://itad.docs.apiary.io/
 */
interface IsThereAnyDealService {
    @GET("v01/deals/list/")
    suspend fun getDeals(
        @Query("key") apiKey: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20,
        @Query("region") region: String? = null,
        @Query("country") country: String? = null,
        @Query("shops") stores: String? = null
    ): Response<DealMeta, DealData>

    data class Response<MetaDataType, DataType : Any>(
        @SerializedName(".deprecated") val deprecated: String? = null,
        @SerializedName(".meta") val meta: MetaDataType? = null,
        @SerializedName("data") val data: DataType
    )

    companion object {
        private const val ENDPOINT = "https://api.isthereanydeal.com/"
    }
}
