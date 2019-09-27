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

import network.obrien.isthereanydeal.data.api.model.IsThereAnyDealResponse
import network.obrien.isthereanydeal.data.deal.model.DealsData
import network.obrien.isthereanydeal.data.deal.model.DealsMeta
import network.obrien.isthereanydeal.data.region.model.RegionByCode
import network.obrien.isthereanydeal.data.store.model.Stores
import network.obrien.isthereanydeal.data.store.model.StoresMeta
import network.obrien.isthereanydeal.data.util.RetrofitService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit service that models the IsThereAnyDeal API
 *
 * https://itad.docs.apiary.io/
 */
interface IsThereAnyDealService : RetrofitService {
    @GET("v01/web/regions/")
    suspend fun getRegions(): Response<IsThereAnyDealResponse<Nothing, RegionByCode>>

    @GET("v01/web/stores/all/")
    suspend fun getAllStores(): Response<IsThereAnyDealResponse<Nothing, Stores>>

    @GET("v02/web/stores/")
    suspend fun getStoresForRegion(
        @Query("region") regionCode: String,
        @Query("country") countryCode: String
    ): Response<IsThereAnyDealResponse<StoresMeta, Stores>>

    @GET("v01/deals/list/")
    suspend fun getDeals(
        @Query("offset") dealOffset: Int = 0,
        @Query("limit") numberOfDeals: Int = 20,
        @Query("region") region: String? = null,
        @Query("country") country: String? = null,
        @Query("shops") stores: String? = null
    ): Response<IsThereAnyDealResponse<DealsMeta, DealsData>>

    companion object {
        const val ENDPOINT = "https://api.isthereanydeal.com/"
    }
}
