package com.ideal.mobileexcercise.services

import com.ideal.mobileexcercise.data.FlickrPhotoAPIResponse
import com.ideal.mobileexcercise.utils.FLICKR_API_KEY
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.*

interface APICalls {
    //api call for the flickr api will return Json response
//    @Headers("Content-Type: application/json", "Accept: /")
    @HTTP(method = "GET", path = "?method=flickr.photos.search&api_key=$FLICKR_API_KEY")
    fun getFlickrRes(@Query("text") search: String,
                     @Query("format") format: String = "json",
                     @Query("nojsoncallback") nojsoncallback: String = "1"): Observable<Response<FlickrPhotoAPIResponse>>
}
