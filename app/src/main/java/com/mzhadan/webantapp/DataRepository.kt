package com.mzhadan.webantapp

import com.google.gson.annotations.SerializedName

data class ImageList(
    val totalItems: Int,
    val itemsPerPage: Int,
    val countOfPages: Int,
    val data: ArrayList<ImageListItem>
)

data class ImageListItem(
    val id: Int,
    val name: String,
    val dateCreate: String,
    val description: String,
    val new: Boolean,
    val popular: Boolean,
    val image: ImagePar,
)

data class ImagePar(
    val id: Int,
    @SerializedName("name")
    val imageUrl: String)

