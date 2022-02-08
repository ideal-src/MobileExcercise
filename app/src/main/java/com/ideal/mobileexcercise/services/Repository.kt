package com.ideal.mobileexcercise.services

import android.util.Log
import com.ideal.mobileexcercise.data.FlickrPhotoAPIResponse
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.ResponseBody
import javax.inject.Inject

class Repository @Inject constructor(private val apiCalls: APICalls) {

    //using RxJava we can subscribe and observe our api call
    fun getFlickerAPI(search: String, next: (code: Int, flickrResponse: FlickrPhotoAPIResponse?, err: ResponseBody?) -> Unit) {

        apiCalls.getFlickrRes(search).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                if (res.code() == 200 ) {
                    if (res.body() != null) {
                        next(res.code(), res.body(), null)
                    } else {
                        next(res.code(), null, res.errorBody())
                    }
                }
            }, { err ->
                Log.e("cccc", "Error ${err.message}")
            })
    }



}