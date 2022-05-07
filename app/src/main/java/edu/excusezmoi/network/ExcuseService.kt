package edu.excusezmoi.network

import retrofit2.Response
import retrofit2.http.*

interface ExcuseService {

    @GET("excuses")
    suspend fun getExcuses(
    ): List<ExcuseNetworkEntity>

    @GET("excuses")
    suspend fun getExcuseByCategory(
        @Query("category") category: String
    ): List<ExcuseNetworkEntity>

    @POST("excuses")
    suspend fun postExcuse(
        @Query("category") category: String,
        @Query("excuse") excuse: String,
    ): Response<Unit>

    @PATCH("excuses/{id}")
    suspend fun patchExcuse(
        @Path("id") id: Int,
        @Query("category") category: String,
        @Query("excuse") excuse: String,
    ): Response<Unit>

    @DELETE("excuses/{id}")
    suspend fun destroyExcuse(
        @Path("id") id: Int
    ): Response<Unit>
}
