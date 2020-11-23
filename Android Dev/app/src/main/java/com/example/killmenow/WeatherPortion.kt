package com.example.killmenow

import android.annotation.SuppressLint
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
import java.lang.Exception
import java.lang.Math.abs
import java.lang.Math.toIntExact
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

class WeatherPortion : AppCompatActivity() {
    val CITY: String = "Kingston,jm"
    val API: String = "5f628f66be850d4777a4468cd8eb8ce0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_portion)

        weatherTask().execute()
    }

    inner class weatherTask(): AsyncTask<String, Void, String>(){

        override fun onPreExecute() {
            Log.d("Success","You're in the try block for pre texecute")
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility=View.VISIBLE
            findViewById<TextView>(R.id.errortext).visibility=View.GONE

        }

        override fun doInBackground(vararg params: String?): String? {

            var response:String?
            try{
                Log.d("Success","You're in the try block for bgexecute")
                response = URL("https://api.openweathermap.org/data/2.5/weather?q=$CITY&units=metric&appid=$API")
                    .readText(Charsets.UTF_8)
            }catch(e:Exception){
                response = null
                Log.d("Success","You're in the catch block for bgexecute")

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

                val temp = main.getString("temp") + "°C"
                val humidity = main.getString("humidity")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")
                val windSpeed = wind.getString("speed")
                val updateAt:Long = jsonObj.getLong("dt")
                val updatedAtText = "Updated at:"+SimpleDateFormat("dd/MM/yyyy hh:mm: a", Locale.ENGLISH).format(Date(updateAt*1000))
                findViewById<TextView>(R.id.Location).text = address
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.update).text= updatedAtText
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