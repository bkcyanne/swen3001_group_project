package com.example.killmenow

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL

class WeatherPortion : AppCompatActivity() {
    val CITY: String = "Kingston,JA"
    val API: String = "5f628f66be850d4777a4468cd8eb8ce0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_portion)

        weatherTask().execute()

    }

    inner class weatherTask() : AsyncTask<String, Void, String>() {


        override fun onPreExecute() {
            super.onPreExecute()


        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                Log.d("Success","You're in the try block for background")
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&appid=$API").readText(
                        Charsets.UTF_8)


            } catch (e: Exception) {
                Log.d("Success","You're in the try catch for background")
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {
                Log.d("Success","You're in the try block for postexecute")
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONObject("weather").getJSONObject("0")
                val temp = main.getString("temp") + "°C"
                val humidity = main.getString("humidity")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")
                val windSpeed = wind.getString("speed")
                findViewById<TextView>(R.id.Location).text = address
                findViewById<TextView>(R.id.temp).text = temp

                findViewById<TextView>(R.id.climate).text = humidity
                findViewById<TextView>(R.id.wind).text = windSpeed

                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE

            } catch (e: java.lang.Exception) {
                Log.d("Success","You're in the catch for postexecute")
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errortext).visibility = View.GONE
            }
        }

    }
}