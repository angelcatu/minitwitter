package com.tzikin.minitwitter.view.viewmodel.repository.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Tweet {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("mensaje")
    @Expose
    var mensaje: String? = null

    @SerializedName("likes")
    @Expose
    var likes: List<Like>? = null

    @SerializedName("user")
    @Expose
    var user: User? = null
}
