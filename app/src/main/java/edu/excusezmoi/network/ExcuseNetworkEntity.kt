package edu.excusezmoi.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ExcuseNetworkEntity(
    @SerializedName("id")
    @Expose
    var id: Int,

    @SerializedName("category")
    @Expose
    var category: String,

    @SerializedName("excuse")
    @Expose
    var text: String
)
