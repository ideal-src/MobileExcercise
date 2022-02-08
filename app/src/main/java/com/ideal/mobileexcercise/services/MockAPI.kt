package com.ideal.mobileexcercise.services

import com.google.gson.GsonBuilder
import com.ideal.mobileexcercise.data.FlickrPhotoAPIResponse
import com.ideal.mobileexcercise.utils.BASE_URL
import com.ideal.mobileexcercise.utils.FLICKR_API_KEY
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

//for Unit testing
interface MockAPI {

    @Headers("Content-Type: application/json", "Accept: /")
    @HTTP(method = "GET", path = "?method=flickr.photos.search&api_key=$FLICKR_API_KEY")
    fun getPromotionContent(@Query("text") search: String,
                            @Query("format") format: String = "json",
                            @Query("nojsoncallback") nojsoncallback: String = "1"): Observable<Response<FlickrPhotoAPIResponse>>


    companion object APIFactory {
        fun create(): MockAPI {
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor {
                    val newRequest: Request = it.request().newBuilder()
                        .addHeader("Connection", "close")
                        .build()
                    it.proceed(newRequest)
                }.build()


            val gson = GsonBuilder()
                .serializeNulls()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

            return retrofit.create(MockAPI::class.java)
        }
    }

}