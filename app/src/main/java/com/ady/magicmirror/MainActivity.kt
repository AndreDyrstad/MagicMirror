package com.ady.magicmirror

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeText(view: View) {

        val textView = findViewById<TextView>(R.id.helloText)

        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts/1"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                textView.text = "Response is: $response"
            },
            { textView.text = "That didn't work!" })

        queue.add(stringRequest)
    }
}