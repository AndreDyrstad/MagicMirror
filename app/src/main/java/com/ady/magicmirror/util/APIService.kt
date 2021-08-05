package com.ady.magicmirror.util

/*class APIService {

    inline fun <reified T> get(context: Context?, supplier: Supplier<T>): T? {
        var res: T? = null
        val gson = Gson()
        val queue = Volley.newRequestQueue(context)
        val url = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=60.39299&lon=5.32415"
        val getRequest: StringRequest = object : StringRequest(
            Method.GET, url,
            Response.Listener { response ->
                println("inside $res")
                res = gson.fromJson(response, T::class.java)
            },
            Response.ErrorListener { error ->
                println(error)
            }
        ) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["User-Agent"] = "MagicMirror"
                return params
            }
        }
        queue.add(getRequest)
        return res
    }
}*/