package com.deloitte.series.coroutine.network.repository

import com.deloitte.series.coroutine.network.Factory
import com.deloitte.series.coroutine.network.exception.GenericException
import com.deloitte.series.coroutine.network.exception.NetworkException
import com.deloitte.series.coroutine.network.exception.RestException
import com.deloitte.series.coroutine.network.model.ApiStatus
import com.deloitte.series.coroutine.network.model.Info
import com.deloitte.series.coroutine.network.model.Result
import com.deloitte.series.coroutine.network.model.SearchResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlinx.coroutines.flow.*

class ApiRepository {

    @Throws(
        RestException::class,
        NetworkException::class,
        CancellationException::class,
        GenericException::class
    )
    suspend fun searchPhotos(search: String, page: Int): Result<SearchResponse, Info> {

        try {
            return withContext(Dispatchers.IO) {
                val searchResponse = async { Factory.service().searchPhotos(search, page) }
                val infoResponse = async { Factory.service().getInfo() }

                val searchResult = searchResponse.await()
                val infoResult = infoResponse.await()

                if (ApiStatus.matches(searchResult.stat) == ApiStatus.OK && ApiStatus.matches(
                        infoResult.stat
                    ) == ApiStatus.OK
                ) {
                    Result(searchResult, infoResult)
                } else {
                    if (ApiStatus.matches(searchResult.stat) == ApiStatus.OK) {
                        throw RestException(
                            infoResult.stat,
                            infoResult.code,
                            infoResult.message
                        )
                    } else {
                        throw RestException(
                            searchResult.stat,
                            searchResult.code,
                            searchResult.message
                        )
                    }
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


    @Throws(
        RestException::class,
        NetworkException::class,
        CancellationException::class,
        GenericException::class
    )
    fun searchPhotosWithFlow(search: String, page: Int): Flow<Result<SearchResponse, Info>> {

        val searchFlow = flow { emit(Factory.service().searchPhotos(search, page)) }

        val infoFlow = flow { emit(Factory.service().getInfo()) }

        return combine(searchFlow, infoFlow) { searchResponse, info ->

            if (ApiStatus.matches(searchResponse.stat) == ApiStatus.OK && ApiStatus.matches(
                    info.stat
                ) == ApiStatus.OK
            ) {
                Result(searchResponse, info)
            } else {
                if (ApiStatus.matches(searchResponse.stat) == ApiStatus.OK) {
                    throw RestException(
                        info.stat,
                        info.code,
                        info.message
                    )
                } else {
                    throw RestException(
                        searchResponse.stat,
                        searchResponse.code,
                        searchResponse.message
                    )
                }
            }
        }.flowOn(Dispatchers.IO)
            .catch { e ->
                throw when (e) {
                    is IOException -> NetworkException()

                    is CancellationException -> CancellationException()

                    else -> GenericException()
                }
            }
    }
}