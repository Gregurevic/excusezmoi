package edu.excusezmoi.network

import retrofit2.http.GET
import retrofit2.http.Path

interface ExcuseService {

    @GET("excuse")
    suspend fun getExcuse(): ExcuseNetworkEntity

    @GET("excuse/{count}")
    suspend fun getExcuses(
        @Path("count") count: Int
    ): List<ExcuseNetworkEntity>

    @GET("excuse/{category}")
    suspend fun getExcuseByCategory(
        @Path("category") category: String
    ): ExcuseNetworkEntity

    @GET("excuse/{category}/{count}")
    suspend fun getExcusesByCategory(
        @Path("category") category: String,
        @Path("count") count: Int
    ): List<ExcuseNetworkEntity>
}