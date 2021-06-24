package com.tzikin.minitwitter.view.viewmodel.repository.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class User {

    @SerializedName("id")
    @Expose
    val id: Int? = null

    @SerializedName("username")
    @Expose
    val username: String? = null

    @SerializedName("descripcion")
    @Expose
    val descripcion: String? = null

    @SerializedName("website")
    @Expose
    val website: String? = null

    @SerializedName("photoUrl")
    @Expose
    val photoUrl: String? = null

    @SerializedName("created")
    @Expose
    val created: String? = null


}