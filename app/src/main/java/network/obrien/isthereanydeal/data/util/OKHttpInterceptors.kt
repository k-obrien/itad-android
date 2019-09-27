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

package network.obrien.isthereanydeal.data.util

import okhttp3.Interceptor

/**
 * An [Interceptor] that adds one or more query parameters to the request
 */
class QueryInterceptor(private vararg val queries: Pair<String, String>) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response = chain.request().run {
        url.newBuilder()
            .apply { queries.forEach { addQueryParameter(it.first, it.second) } }.build()
            .let { url -> newBuilder().url(url).build() }
            .let { request -> chain.proceed(request) }
    }
}
