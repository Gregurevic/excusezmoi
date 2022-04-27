package edu.excusezmoi.network

import retrofit2.http.*

interface ExcuseService {

    @GET("excuses")
    suspend fun getExcuses(
    ): List<ExcuseNetworkEntity>

    @GET("excuses?category={category}")
    suspend fun getExcuseByCategory(
        @Path("category") category: String
    ): List<ExcuseNetworkEntity>

    @POST("excuses?category={category}&excuse={excuse}")
    suspend fun postExcuse(
        @Path("category") category: String,
        @Path("excuse") excuse: String,
    )

    @PATCH("excuses?id={id}&category={category}&excuse={excuse}")
    suspend fun patchExcuse(
        @Path("id") id: Int,
        @Path("category") category: String,
        @Path("excuse") excuse: String,
    )

    @DELETE("excuses?id={id}")
    suspend fun destroyExcuse(
        @Path("id") id: Int
    )
}
