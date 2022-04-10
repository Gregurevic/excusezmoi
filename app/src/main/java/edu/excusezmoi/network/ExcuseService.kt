package edu.excusezmoi.network

import retrofit2.http.GET

interface ExcuseService {

    @GET("excuse")
    suspend fun getExcuse(): ExcuseNetworkEntity

    ///...
}