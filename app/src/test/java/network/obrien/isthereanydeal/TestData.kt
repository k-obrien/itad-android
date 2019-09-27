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

const val PRICE_ONLY_DECIMALS_STRING: String = "0.96"
const val PRICE_ZERO_DECIMALS_STRING: String = "96"
const val PRICE_ONE_DECIMAL_STRING: String = "96.8"
const val PRICE_TWO_DECIMALS_STRING: String = "96.85"
const val PRICE_THREE_DECIMALS_STRING: String = "96.854"

const val PRICE_ONLY_DECIMALS_LONG: Long = 96
const val PRICE_ZERO_DECIMALS_LONG: Long = 9600
const val PRICE_ONE_DECIMAL_LONG: Long = 9680
const val PRICE_TWO_DECIMALS_LONG: Long = 9685

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
    dealPrice = 2647,
    regularPrice = 9685,
    discountPercent = 73,
    timeAddedSecondsUtc = 1568289504,
    store = store,
    drm = drm,
    links = links
)

val dealTwo: Deal = Deal(
    gameId = "aaagame",
    gameTitle = "AAA Game",
    dealPrice = 8000,
    regularPrice = 10000,
    discountPercent = 20,
    timeAddedSecondsUtc = 1568289504,
    store = store,
    drm = drm,
    links = links
)

val dealsMeta: DealsMeta = DealsMeta(currency = "AUD")

val dealsResponse: IsThereAnyDealResponse<DealsMeta, DealsData> =
    IsThereAnyDealResponse(meta = dealsMeta, data = DealsData(2, listOf(dealOne, dealTwo)))

val errorResponseBody: ResponseBody = "Error".toResponseBody("".toMediaTypeOrNull())
