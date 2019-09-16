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

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import network.obrien.isthereanydeal.data.Resource
import network.obrien.isthereanydeal.data.api.IsThereAnyDealService
import network.obrien.isthereanydeal.dealsResponse
import network.obrien.isthereanydeal.errorResponseBody
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import retrofit2.Response
import java.io.IOException

@ExtendWith(MockitoExtension::class)
class DealsRemoteDataSourceTest {
    private lateinit var service: IsThereAnyDealService
    private lateinit var dataSource: DealsRemoteDataSource

    @BeforeEach
    fun setUp() {
        service = mock()
        dataSource = DealsRemoteDataSource(service)
    }

    @Test
    @DisplayName("Given a successful response when requesting a list of deals, then the result is a success")
    fun getDeals_whenRequestSuccessful(): Unit =
        runBlocking {
            whenever(service.getDeals()).thenReturn(Response.success(dealsResponse))

            dataSource.getDeals().let { response ->
                assertThat(response).isNotNull()
                assertThat(response).isEqualTo(Resource.Success(dealsResponse))
            }
        }

    @Test
    @DisplayName("Given a successful response when requesting a list of deals from specific stores, then the result is a success")
    fun getDeals_forSelectedStores_whenRequestSuccessful(): Unit = runBlocking {
        whenever(service.getDeals(stores = "steam,gog")).thenReturn(Response.success(dealsResponse))

        dataSource.getDeals(stores = listOf("steam", "gog")).let { response ->
            assertThat(response).isNotNull()
            assertThat(response).isEqualTo(Resource.Success(dealsResponse))
        }
    }

    @Test
    @DisplayName("Given a successful but empty response when requesting a list of deals, then the result is an error")
    fun getDeals_whenRequestSuccessful_withEmptyResponse(): Unit = runBlocking {
        whenever(service.getDeals()).thenReturn(Response.success(null))

        dataSource.getDeals().let { response ->
            assertThat(response).isNotNull()
            assertThat(response).isInstanceOf(Resource.Error::class.java)
        }
    }

    @Test
    @DisplayName("Given a failed response when requesting a list of deals, then the result is an error")
    fun getDeals_whenRequestFailed(): Unit = runBlocking {
        whenever(service.getDeals()).thenReturn(Response.error(400, errorResponseBody))

        dataSource.getDeals().let { response ->
            assertThat(response).isNotNull()
            assertThat(response).isInstanceOf(Resource.Error::class.java)
        }
    }

    @Test
    @DisplayName("Given a thrown exception when requesting a list of deals, then the result is an error")
    fun getDeals_whenExceptionThrown(): Unit = runBlocking {
        whenever(service.getDeals()).thenAnswer { throw IOException() }

        dataSource.getDeals().let { response ->
            assertThat(response).isNotNull()
            assertThat(response).isInstanceOf(Resource.Error::class.java)
        }
    }
}