package com.ideal.mobileexcercise

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ideal.mobileexcercise.services.MockAPI
import com.ideal.mobileexcercise.utils.BASE_URL
import com.ideal.mobileexcercise.utils.FLICKR_API_KEY
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ideal.mobileexcercise", appContext.packageName)
    }


    private lateinit var service: MockAPI

    var server = MockWebServer()


    //simple test to see if apicall is successful at response body is not empty
    @Test
    fun mockAPI() {
        server = MockWebServer()
        service = MockAPI.create()

        service.getPromotionContent("laptop").subscribeOn(Schedulers.io()).observeOn(
            AndroidSchedulers.mainThread())
            .subscribe({ res ->
                assertEquals(res.isSuccessful, true)
                assertEquals(res.body() != null, true)

            }, { err ->
                Log.e("cccc", "Error ${err.message}")
            })

    }

}