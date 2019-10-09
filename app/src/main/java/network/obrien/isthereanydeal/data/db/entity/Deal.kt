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
import androidx.room.PrimaryKey
import network.obrien.isthereanydeal.data.db.entity.Deal.Companion.COLUMN_GAME_ID
import network.obrien.isthereanydeal.data.db.entity.Deal.Companion.COLUMN_STORE_ID
import network.obrien.isthereanydeal.data.db.entity.Deal.Companion.TABLE_NAME
import org.threeten.bp.OffsetDateTime

@Entity(
    tableName = TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            parentColumns = [Game.COLUMN_ID],
            childColumns = [COLUMN_GAME_ID],
            onUpdate = CASCADE,
            onDelete = CASCADE
        ),
        ForeignKey(
            entity = Store::class,
            parentColumns = [Store.COLUMN_ID],
            childColumns = [COLUMN_STORE_ID],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
data class Deal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)
    val id: Long,

    @ColumnInfo(name = COLUMN_GAME_ID)
    val gameId: String,

    @ColumnInfo(name = COLUMN_STORE_ID)
    val storeId: String,

    @ColumnInfo(name = COLUMN_ADDED_DATETIME)
    val added: OffsetDateTime,

    @ColumnInfo(name = COLUMN_PRICE_CENTS)
    val price: Long,

    @ColumnInfo(name = COLUMN_PERCENT_DISCOUNT)
    val percentDiscount: Int
) {
    companion object {
        const val TABLE_NAME = "deal"
        const val COLUMN_ID = "id"
        const val COLUMN_GAME_ID = "game_id"
        const val COLUMN_STORE_ID = "store_id"
        const val COLUMN_ADDED_DATETIME = "added_datetime"
        const val COLUMN_PRICE_CENTS = "price_cents"
        const val COLUMN_PERCENT_DISCOUNT = "percent_discount"
    }
}
