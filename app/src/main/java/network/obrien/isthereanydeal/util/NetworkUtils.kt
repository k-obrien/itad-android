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

import network.obrien.isthereanydeal.BuildConfig
import network.obrien.isthereanydeal.data.Resource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit

fun httpClient(): OkHttpClient = OkHttpClient.Builder()
    .apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().apply { level = Level.BODY })
        }
    }
    .build()

inline fun <reified T> Retrofit.Builder.service(
    baseUrl: String,
    httpClient: OkHttpClient,
    vararg converterFactories: Converter.Factory
): T {
    return baseUrl(baseUrl)
        .client(httpClient)
        .apply { converterFactories.forEach { factory -> addConverterFactory(factory) } }
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
