package com.example.weatherapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<TextView>(R.id.result)
        val cityName = findViewById<EditText>(R.id.etCity)
        val getButton : Button = findViewById(R.id.btnGet)
        val ImageView = findViewById<ImageView>(R.id.imageView)

        val retro = Retrofit.getInstance().create(GetWeatherAPI::class.java)
        getButton.setOnClickListener(View.OnClickListener {
            var url  ="weather?q=${getCityName(cityName)}&appid=3f60677cfc78f343523804a2cb338e34"

        val results = retro.getWeatherData(url)
        results.enqueue(object : Callback<WeatherC>{
            override fun onResponse(call: Call<WeatherC>, response: Response<WeatherC>) {
                if(response.isSuccessful)
                    text.text = response?.body()?.weather?.get(0)?.description
                val icon = response.body()?.weather?.get(0)?.icon
                val imageURL = "https://openweathermap.org/img/wn/$icon@4x.png"
                Glide.with(applicationContext).load(imageURL).into(ImageView)

            }

            override fun onFailure(call: Call<WeatherC>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    })
    }

    private fun getCityName(cityName: EditText): String?= cityName.text.toString()




}


