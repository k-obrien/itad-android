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

package network.obrien.isthereanydeal.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Index
import network.obrien.isthereanydeal.data.db.entity.StoreListing.Companion.COLUMN_GAME_ID
import network.obrien.isthereanydeal.data.db.entity.StoreListing.Companion.COLUMN_STORE_ID
import network.obrien.isthereanydeal.data.db.entity.StoreListing.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    primaryKeys = [COLUMN_STORE_ID, COLUMN_GAME_ID],
    foreignKeys = [
        ForeignKey(
            entity = Store::class,
            parentColumns = [Store.COLUMN_ID],
            childColumns = [COLUMN_STORE_ID],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Game::class,
            parentColumns = [Game.COLUMN_ID],
            childColumns = [COLUMN_GAME_ID],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ],
    indices = [Index(value = [COLUMN_STORE_ID, COLUMN_GAME_ID], unique = true)]
)
data class StoreListing(
    @ColumnInfo(name = COLUMN_STORE_ID)
    val storeId: String,

    @ColumnInfo(name = COLUMN_GAME_ID)
    val gameId: String,

    @ColumnInfo(name = COLUMN_PRICE_CENTS)
    val priceInCents: Long,

    @ColumnInfo(name = COLUMN_PURCHASE_URL)
    val purchaseUrl: String,

    @ColumnInfo(name = COLUMN_HISTORICAL_LOW)
    val historicalLow: Boolean
) {
    companion object {
        const val TABLE_NAME = "store_listing"
        const val COLUMN_STORE_ID = "store_id"
        const val COLUMN_GAME_ID = "game_id"
        const val COLUMN_PRICE_CENTS = "price_cents"
        const val COLUMN_PURCHASE_URL = "purchase_url"
        const val COLUMN_HISTORICAL_LOW = "historical_low"
    }
}
