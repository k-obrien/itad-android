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

package network.obrien.isthereanydeal.data.deal

import network.obrien.isthereanydeal.data.Resource
import network.obrien.isthereanydeal.data.api.IsThereAnyDealService
import network.obrien.isthereanydeal.data.api.model.IsThereAnyDealResponse
import network.obrien.isthereanydeal.data.deal.model.DealsData
import network.obrien.isthereanydeal.data.deal.model.DealsMeta
import network.obrien.isthereanydeal.util.errorString
import network.obrien.isthereanydeal.util.requestCatching
import java.io.IOException
import javax.inject.Inject

class DealsRemoteDataSource @Inject constructor(private val service: IsThereAnyDealService) {
    suspend fun getDeals(
        apiKey: String,
        offset: Int = 0,
        limit: Int = 20,
        region: String? = null,
        country: String? = null,
        stores: List<String>? = null
    ): Resource<IsThereAnyDealResponse<DealsMeta, DealsData>> = requestCatching {
        service.getDeals(apiKey, offset, limit, region, country, stores?.joinToString(","))
            .let { response ->
                response.body()
                    ?.takeIf { response.isSuccessful }?.let { body -> Resource.Success(body) }
                    ?: Resource.Error(IOException(response.errorString))
            }
    }
}