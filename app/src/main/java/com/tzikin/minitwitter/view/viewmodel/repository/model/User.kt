package com.tzikin.minitwitter.view.viewmodel.repository.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class User {

    @SerializedName("id")
    @Expose
    private val id: Int? = null

    @SerializedName("username")
    @Expose
    private val username: String? = null

    @SerializedName("descripcion")
    @Expose
    private val descripcion: String? = null

    @SerializedName("website")
    @Expose
    private val website: String? = null

    @SerializedName("photoUrl")
    @Expose
    private val photoUrl: String? = null

    @SerializedName("created")
    @Expose
    private val created: String? = null
}