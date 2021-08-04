package com.ady.magicmirror.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ady.magicmirror.io.SoMeTypeOfEndPoints
import com.ady.magicmirror.util.enqueue
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SoMeViewModel : ViewModel() {

    val weatherText: LiveData<String> = MutableLiveData(getSoMeData())

    private fun getSoMeData(): String {
        val soMeLogger = HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        val soMeOkHttp = OkHttpClient()
            .newBuilder()
            .apply {
                addInterceptor(soMeLogger)
            }.build()

        return Retrofit
            .Builder()
            .apply {
                addConverterFactory(GsonConverterFactory.create())
                baseUrl("https://someUrl.someDomain/")
                client(soMeOkHttp)
            }
            .build()
            .create(SoMeTypeOfEndPoints::class.java)
            .getSomeEndPoint()
            .enqueue() //Normally (object : Callback<SomeModel> { ... })
    }
}