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

import com.google.common.truth.Truth.assertThat
import network.obrien.isthereanydeal.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class MoshiJsonAdaptersTest {
    @Test
    @DisplayName("Given a valid string representation of a decimal number, then the result is a long")
    fun priceAdapter_fromJson() = with(PriceAdapter()) {
        assertThat(fromJson(PRICE_ONLY_DECIMALS_STRING)).isEqualTo(PRICE_ONLY_DECIMALS_LONG)
        assertThat(fromJson(PRICE_ZERO_DECIMALS_STRING)).isEqualTo(PRICE_ZERO_DECIMALS_LONG)
        assertThat(fromJson(PRICE_ONE_DECIMAL_STRING)).isEqualTo(PRICE_ONE_DECIMAL_LONG)
        assertThat(fromJson(PRICE_TWO_DECIMALS_STRING)).isEqualTo(PRICE_TWO_DECIMALS_LONG)
        assertThat(fromJson(PRICE_THREE_DECIMALS_STRING)).isEqualTo(PRICE_TWO_DECIMALS_LONG)
    }

    @Test
    @DisplayName("Given a long, then the result is a valid string representation of a decimal number")
    fun priceAdapter_toJson() = with(PriceAdapter()) {
        assertThat(toJson(PRICE_ONLY_DECIMALS_LONG)).isEqualTo(PRICE_ONLY_DECIMALS_STRING)
        assertThat(toJson(PRICE_ZERO_DECIMALS_LONG)).isEqualTo(PRICE_ZERO_DECIMALS_STRING)
        assertThat(toJson(PRICE_ONE_DECIMAL_LONG)).isEqualTo(PRICE_ONE_DECIMAL_STRING)
        assertThat(toJson(PRICE_TWO_DECIMALS_LONG)).isEqualTo(PRICE_TWO_DECIMALS_STRING)
    }
}
