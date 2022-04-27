package edu.excusezmoi.network

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
    )

    @PATCH("excuses")
    suspend fun patchExcuse(
        @Query("id") id: Int,
        @Query("category") category: String,
        @Query("excuse") excuse: String,
    )

    @DELETE("excuses")
    suspend fun destroyExcuse(
        @Query("id") id: Int
    )
}
