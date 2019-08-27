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

package network.obrien.isthereanydeal.domain.usecase

import network.obrien.isthereanydeal.domain.model.Deal
import network.obrien.isthereanydeal.domain.model.PagedResource
import network.obrien.isthereanydeal.domain.repository.DealsRepository
import javax.inject.Inject

class GetDeals @Inject constructor(
    private val dealsRepository: DealsRepository
) {
    suspend operator fun invoke(stores: List<String>? = null): PagedResource<Deal> =
        dealsRepository.getDeals(stores)
}