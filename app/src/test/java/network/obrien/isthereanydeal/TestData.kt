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

package network.obrien.isthereanydeal

import network.obrien.isthereanydeal.data.api.model.IsThereAnyDealResponse
import network.obrien.isthereanydeal.data.deal.model.*
import network.obrien.isthereanydeal.data.store.model.Store
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import java.math.BigDecimal

val store: Store = Store(
    id = "store",
    name = "Store"
)

val drm: DrmPlatforms = listOf("DRM1", "DRM2")

val links: DealLinks = DealLinks(
    purchase = "https://www.game.com/purchase",
    info = "https://www.game.com/info"
)

val dealOne: Deal = Deal(
    gameId = "classicgame",
    gameTitle = "Classic Game",
    discountPrice = BigDecimal(1),
    regularPrice = BigDecimal(10),
    discountPercent = 90,
    timeAddedSecondsUtc = 1568289504,
    store = store,
    drm = drm,
    links = links
)

val dealTwo: Deal = Deal(
    gameId = "aaagame",
    gameTitle = "AAA Game",
    discountPrice = BigDecimal(80),
    regularPrice = BigDecimal(100),
    discountPercent = 20,
    timeAddedSecondsUtc = 1568289504,
    store = store,
    drm = drm,
    links = links
)

val meta: DealsMeta = DealsMeta(currency = "AUD")

val dealsResponse: IsThereAnyDealResponse<DealsMeta, DealsData> =
    IsThereAnyDealResponse(meta = meta, data = DealsData(2, listOf(dealOne, dealTwo)))

val errorResponseBody: ResponseBody = "Error".toResponseBody("".toMediaTypeOrNull())
