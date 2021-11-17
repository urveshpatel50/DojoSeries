package com.deloitte.series.coroutine.network.repository

import com.deloitte.series.coroutine.network.Factory
import com.deloitte.series.coroutine.network.exception.GenericException
import com.deloitte.series.coroutine.network.exception.NetworkException
import com.deloitte.series.coroutine.network.exception.RestException
import com.deloitte.series.coroutine.network.model.ApiStatus
import com.deloitte.series.coroutine.network.model.Result
import com.deloitte.series.coroutine.network.model.SearchResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ApiRepository {

    @Throws(RestException::class, NetworkException::class, CancellationException::class, GenericException::class)
    suspend fun searchPhotos(search: String, page: Int): Result<SearchResponse> {

        try {
            return withContext(Dispatchers.IO) {
                val response = Factory.service().searchPhotos(search, page)

                if (ApiStatus.matches(response.stat) == ApiStatus.OK) {
                    Result(response)
                } else {
                    throw RestException(response.stat, response.code, response.message)
                }
            }
        } catch (throwable: Throwable) {
            throw when (throwable) {
                is IOException -> NetworkException()

                is CancellationException -> CancellationException()

                else -> GenericException()
            }
        }
    }
}