/*
 * Copyright (C) 2018-present Kieran O'Brien
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package network.obrien.isthereanydeal.api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import java.io.IOException


abstract class ServiceTest {
    protected lateinit var server: MockWebServer
    protected lateinit var service: DealsService

    @Before
    fun setup() {
        server = MockWebServer()
        service = Retrofit.Builder().service(true, server.url("/"))
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    protected fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val source = javaClass.classLoader?.getResourceAsStream("api-response/$fileName")
            ?.source()?.buffer() ?: throw IOException()

        val response = MockResponse().apply {
            headers.forEach { (key, value) -> addHeader(key, value) }
            setBody(source.readString(Charsets.UTF_8))
        }

        server.enqueue(response)
    }
}