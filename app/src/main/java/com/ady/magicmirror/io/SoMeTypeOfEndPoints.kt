package com.ady.magicmirror.io

import com.ady.magicmirror.models.SomeKindOfCollection
import com.ady.magicmirror.util.someConstant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface SoMeTypeOfEndPoints {
    @Headers("soMeHeaders: ")
    @GET("/backslash/trouble/incoming/{id}")
    fun getSomeEndPoint(
        @Query("id") someId: String = someConstant
    ): Call<SomeKindOfCollection>
}