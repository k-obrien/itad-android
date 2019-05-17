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

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import network.obrien.isthereanydeal.vo.Deal
import network.obrien.isthereanydeal.vo.Links
import network.obrien.isthereanydeal.vo.Store
import org.junit.Test
import java.math.BigDecimal


class DealsServiceTest : ServiceTest() {
    @Test
    fun dealsService_getDeals() = runBlocking {
        enqueueResponse("deals.json")

        val response = service.getDeals(
            key = "api_key",
            offset = 40,
            limit = 100,
            region = "au2",
            country = "AU",
            shops = "steam,gog"
        ).await()

        val request = server.takeRequest()
        assertThat(request.path).isEqualTo("/v01/deals/list/?key=api_key&offset=40&limit=100&region=au2&country=AU&shops=steam%2Cgog")

        assertThat(response).isNotNull()

        assertThat(response.meta).isNotNull()
        assertThat(response.meta!!.currency).isEqualTo("AUD")

        val data = response.data
        assertThat(data).isNotNull()
        assertThat(data.count).isEqualTo(22920)

        val deals = data.deals
        assertThat(deals).isNotNull()
        assertThat(deals.size).isEqualTo(20)

        val deal = deals.getOrNull(1)
        assertThat(deal).isNotNull()
        assertThat(deal).isInstanceOf(Deal::class.java)
        assertThat(deal!!.plain).isEqualTo("armaiiiapexedition")
        assertThat(deal.title).isEqualTo("Arma 3 Apex Edition")
        assertThat(deal.currentPrice).isEqualTo(BigDecimal("33.98"))
        assertThat(deal.previousPrice).isEqualTo(BigDecimal("99.95"))
        assertThat(deal.discountPercent).isEqualTo(66)
        assertThat(deal.timeAdded).isEqualTo(1558099845L)

        val store = deal.store
        assertThat(store).isNotNull()
        assertThat(store).isInstanceOf(Store::class.java)
        assertThat(store.id).isEqualTo("steam")
        assertThat(store.name).isEqualTo("Steam")

        val drm = deal.drm
        assertThat(drm).isNotNull()
        assertThat(drm.size).isEqualTo(1)
        assertThat(drm.firstOrNull()).isEqualTo("steam")

        val links = deal.links
        assertThat(links).isNotNull()
        assertThat(links).isInstanceOf(Links::class.java)
        assertThat(links.purchase).isEqualTo("https://store.steampowered.com/app/639600/")
        assertThat(links.info).isEqualTo("https://isthereanydeal.com/game/armaiiiapexedition/info/")
    }
}
