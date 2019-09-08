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

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import retrofit2.converter.moshi.MoshiConverterFactory
import java.math.BigDecimal

fun moshiConverterFactory(adapters: List<Any> = emptyList()): MoshiConverterFactory =
    MoshiConverterFactory.create(
        Moshi.Builder().apply { adapters.forEach { adapter -> add(adapter) } }.build()
    )

object BigDecimalAdapter {
    @FromJson
    fun fromJson(json: String): BigDecimal = BigDecimal(json)

    @ToJson
    fun toJson(bigDecimal: BigDecimal): String = bigDecimal.toString()
}