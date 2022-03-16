package com.deloitte.series.coroutine.network.model

data class Result<FirstResponse, SecondResponse>(val firstResponse: FirstResponse?  = null,
                                                 val secondResponse: SecondResponse?  = null)