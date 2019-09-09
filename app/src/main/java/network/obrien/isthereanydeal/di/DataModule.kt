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

package network.obrien.isthereanydeal.di

import dagger.Lazy
import dagger.Module
import dagger.Provides
import network.obrien.isthereanydeal.BuildConfig
import network.obrien.isthereanydeal.data.api.IsThereAnyDealService
import network.obrien.isthereanydeal.util.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient = httpClient()

    @Provides
    @IsThereAnyDealProtectedApi
    fun provideOkHttpClient(httpClient: OkHttpClient): OkHttpClient =
        httpClient.newBuilder().addInterceptor(ApiKeyInterceptor(BuildConfig.ITAD_API_KEY)).build()

    @Provides
    @Singleton
    fun provideIsThereAnyDealService(
        httpClient: Lazy<OkHttpClient>
    ): IsThereAnyDealService = Retrofit.Builder().service(
        IsThereAnyDealService.ENDPOINT,
        httpClient.get(),
        moshiConverterFactory(BigDecimalAdapter)
    )

    @Provides
    @Singleton
    @IsThereAnyDealProtectedApi
    fun provideProtectedIsThereAnyDealService(
        @IsThereAnyDealProtectedApi httpClient: Lazy<OkHttpClient>
    ): IsThereAnyDealService = Retrofit.Builder().service(
        IsThereAnyDealService.ENDPOINT,
        httpClient.get(),
        moshiConverterFactory(BigDecimalAdapter)
    )
}