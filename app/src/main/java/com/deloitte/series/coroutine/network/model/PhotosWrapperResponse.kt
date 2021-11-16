package com.deloitte.series.coroutine.network.model

import java.io.Serializable

data class SearchResponse(
    val photos: Photos?,
    val stat: String,
    val code: Int?,
    val message: String?
) : Serializable

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val total: Int,
    val photo: List<PhotoMetaData>
) : Serializable

data class PhotoMetaData(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
) : Serializable
