package com.example.rxjava

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : Activity() {

    private lateinit var textView: TextView
    private lateinit var requestButton: Button

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        textView = findViewById(R.id.weatherText)
        requestButton = findViewById(R.id.requestButton)
        requestButton.setOnClickListener {
            textView.text = ""
            val client = ApiClient.retrofit.create(ApiInterface::class.java)
            client.getWeatherByCity("Kyiv")
                .subscribeOn(Schedulers.io())
                .map { response ->
                    mapToDisplayItem(response)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    textView.text =
                        "Temperature: ${result.temperature};\nWind: ${result.wind};\nDescription: ${result.description}"
                }, { error ->
                    Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                })
        }
    }
}