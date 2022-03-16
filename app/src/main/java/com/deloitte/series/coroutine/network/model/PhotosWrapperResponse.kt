package com.deloitte.series.coroutine.network.model

import java.io.Serializable

data class SearchResponse(
    val photos: Photos?,
    val stat: String,
    val code: Int?,
    val message: String?
) : Serializable

data class Info(
    val photo: PhotoInfo?,
    val stat: String,
    val code: Int?,
    val message: String?
) : Serializable

data class PhotoInfo(
    val id: String?,
    val title: Title?,
) : Serializable

data class Title(val _content:String) : Serializable

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
