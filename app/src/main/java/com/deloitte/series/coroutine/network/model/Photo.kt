package com.deloitte.series.coroutine.network.model

import java.io.Serializable

data class Photo(
    val id: String,
    val url: String,
    val title: String
) : Serializable
