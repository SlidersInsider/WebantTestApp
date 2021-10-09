package com.mzhadan.webantapp.data.api

import com.mzhadan.webantapp.ImageList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WebantApi {

    @GET("photos")
    fun getNewImages(@Query("limit") imageLimit: Int,
                     @Query("new") newCategory: Boolean,
                     @Query("page") pageNumber: Int): Single<ImageList>

    @GET("photos")
    fun getPopularImages(@Query("limit") imageLimit: Int,
                         @Query("popular") popularCategory: Boolean,
                         @Query("page") pageNumber: Int): Single<ImageList>
}