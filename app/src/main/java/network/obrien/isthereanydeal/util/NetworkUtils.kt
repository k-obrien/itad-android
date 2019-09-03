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

package network.obrien.isthereanydeal.util

import com.squareup.moshi.Moshi
import network.obrien.isthereanydeal.data.Resource
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

inline fun <reified T> Retrofit.Builder.service(debug: Boolean, baseUrl: HttpUrl): T {
    val httpClient = OkHttpClient.Builder()

    if (debug) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
    }

    val moshi = Moshi.Builder().add(BigDecimalAdapter).build()

    return baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient.build())
        .build()
        .create(T::class.java)
}

/**
 * Map exceptional API responses to [Resource.Error]s
 */
suspend fun <T : Any> requestCatching(request: suspend () -> Resource<T>): Resource<T> =
    try {
        request()
    } catch (e: Exception) {
        Resource.Error(e)
    }

val Response<*>.errorString
    get() = "API request failed -- URL: ${raw().request.url}; Code: ${code()}; Error: ${message()}"
