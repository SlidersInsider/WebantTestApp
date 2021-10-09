package com.mzhadan.webantapp.data.providers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mzhadan.webantapp.data.api.WebantApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {

    lateinit var mRetrofit: Retrofit
    lateinit var okHttpClient: OkHttpClient
    lateinit var gson: Gson

    fun provideApiClient(): WebantApi {
        gson = GsonBuilder().create()
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        mRetrofit = Retrofit.Builder()
            .baseUrl("http://gallery.dev.webant.ru/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        return mRetrofit.create(WebantApi::class.java)
    }
}