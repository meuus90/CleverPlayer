/*
 * Copyright (C)  2020 MeUuS90
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

package com.network.clever.data.datasource.network

import androidx.lifecycle.LiveData
import com.meuus.base.network.ApiResponse
import com.network.clever.data.datasource.model.item.PlaylistListModel
import retrofit2.http.GET

interface FirebaseAPI {
    @GET("v1/contents/payload.json")
    fun getPlaylists(): LiveData<ApiResponse<PlaylistListModel>>
}