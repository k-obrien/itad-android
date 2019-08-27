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

package network.obrien.isthereanydeal.domain.model

import java.math.BigDecimal

data class Deal(
    val gameId: String,
    val gameTitle: String,
    val discountPrice: BigDecimal,
    val regularPrice: BigDecimal,
    val discountPercent: Int,
    val timeAddedSecondsUtc: Long,
    val storeName: String,
    val purchaseUrl: String
)
