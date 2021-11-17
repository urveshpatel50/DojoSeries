package com.deloitte.series.coroutine.network

import com.deloitte.series.coroutine.network.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    //https://api.flickr.com/services/rest/?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=96358825614a5d3b1a1c3fd87fca2b47&text=Android%20logo&page=0
    //https://farm66.static.flickr.com/65535/51680336918_69fb00f8cb.jpg

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=96358825614a5d3b1a1c3fd87fca2b47")
    suspend fun searchPhotos(@Query(value = "text") text: String, @Query(value = "page") page: Int) : SearchResponse
}
