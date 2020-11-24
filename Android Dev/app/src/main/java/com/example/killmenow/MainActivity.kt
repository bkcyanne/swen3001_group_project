package com.example.killmenow

import android.content.Intent

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.annotation.NonNull

import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.from
import androidx.biometric.BiometricPrompt
import android.hardware.biometrics.BiometricPrompt.AuthenticationCallback
import android.os.AsyncTask
import android.util.Log
import androidx.biometric.BiometricPrompt.*
import androidx.core.content.ContextCompat

import androidx.lifecycle.ViewModelProvider

import androidx.room.Dao
import com.example.database.User
import com.example.database.UserDao
import com.example.database.UserDatabase
import com.example.database.UserViewModel
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.ConnectionResult


import java.lang.Thread.sleep
import java.util.Date.from
import java.util.concurrent.Executor


class MainActivity() : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{

    lateinit var uN: String
    lateinit var pW: String
    lateinit var uNInput: EditText
    lateinit var pWInput: EditText
    private var counter: Int = 2
    lateinit var jumpStartSignInButton: Button
    lateinit var jumpStartGoogleSignInResult :GoogleSignInResult
    lateinit var createAccUserViewModel: UserViewModel
    lateinit var userList :List<User>
    lateinit var fingerIcon:ImageView
    lateinit var bioManager:BiometricManager
    lateinit var bioPrompt:BiometricPrompt
    lateinit var promptInfo: BiometricPrompt.PromptInfo

    lateinit var userdb: User
    lateinit var executor: Executor

    val firstUser:User=User(0,"Corey","Corey@gmail.com","somepassword")

     @RequiresApi(Build.VERSION_CODES.Q)

     override fun onCreate(savedInstanceState: Bundle?) {
        val userDao =UserDatabase.getDatabase(this).userDao()
         createAccUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)


         uNInput = findViewById<EditText>(R.id.Username)
         pWInput = findViewById<EditText>(R.id.Password)

         for (x in 0 until 2) {
             val loginAccButton = findViewById<Button>(R.id.loginButton)
             loginAccButton.setOnClickListener {
                 uN = uNInput.text.toString()
                 pW = pWInput.text.toString()




                 if (uN==""&&pW=="") {
                     Toast.makeText(this, "User has not been created", Toast.LENGTH_SHORT).show()

                    Toast.makeText(this, "Welcome back $uN!", Toast.LENGTH_SHORT).show()
                    // sleep(3000)


                     val intent = Intent(this, WeatherPortion::class.java)
                     startActivity(intent)
                     finish()

                 } else {
                     Toast.makeText(
                         this,
                         "Invalid Credentials, $counter attempts remaining",
                         Toast.LENGTH_SHORT
                     ).show()
                     counter--
                 }
             }
         }


         val noAccButton = findViewById<Button>(R.id.noAccButton)
         noAccButton.setOnClickListener {
             val intent = Intent(this,accCreationPage::class.java)
             startActivity(intent)
             finish()

         }

         val jumpStartGSO =
             GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
         val jumpStartGoogleApiClient =
             GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(
                 Auth.GOOGLE_SIGN_IN_API, jumpStartGSO
             ).build()

         jumpStartSignInButton = findViewById<Button>(R.id.GoogleSignUp)
         jumpStartSignInButton.setOnClickListener {


             val jumpStartGoogleIntent =
                 Auth.GoogleSignInApi.getSignInIntent(jumpStartGoogleApiClient)
             startActivityForResult(jumpStartGoogleIntent, SIGN_IN)
         }

         //fingerprint stuff here
         fingerIcon = findViewById<ImageView>(R.id.FingereprintIcon)
         bioManager = BiometricManager.from(this)

             when (bioManager.canAuthenticate()) {
                 BiometricManager.BIOMETRIC_SUCCESS -> {


                 }


                 BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                     Toast.makeText(this, "Sorry, Fingerprint is Unavailable", Toast.LENGTH_SHORT)
                         .show()

                 }
                 BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                     Toast.makeText(
                         this,
                         "Add a fingerprint to your device in order to use this",
                         Toast.LENGTH_SHORT
                     ).show()
                 }
                 BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                     Toast.makeText(
                         this,
                         "Sorry,this system does not support Fingerprint Authentication",
                         Toast.LENGTH_SHORT
                     ).show()

                 }
                 else -> {
                     Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()

                 }
             }
             executor = ContextCompat.getMainExecutor(this)
             bioPrompt = BiometricPrompt(this, executor, object :
                 BiometricPrompt.AuthenticationCallback() {
                 override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                     super.onAuthenticationError(errorCode, errString)
                 }

                 override fun onAuthenticationSucceeded(result: AuthenticationResult) {
                     super.onAuthenticationSucceeded(result)
                     Toast.makeText(this@MainActivity, "WE DID IT BOYZZZ", Toast.LENGTH_SHORT)
                         .show()
                     navToHomePage()
                 }


                 override fun onAuthenticationFailed() {
                     super.onAuthenticationFailed()
                 }

             })

            promptInfo =object:BiometricPrompt.PromptInfo.Builder(){}
                .setTitle("Login")
                .setDescription("Use your fingerprint to login")
                .setNegativeButtonText("Cancel")
                .build()


         fingerIcon.setOnClickListener{

             bioPrompt.authenticate(promptInfo)
         }
     }



     override fun onConnectionFailed(p0: ConnectionResult) {
        return
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)

            if(requestCode == SIGN_IN){

                jumpStartGoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)!!
                if(jumpStartGoogleSignInResult.isSuccess()){
                    Toast.makeText(this,"Successfull up to this lane",Toast.LENGTH_SHORT).show()
                    sleep(3000)

                   val jumpStartGoogleIntent = Intent(this,accCreationPage::class.java)
                    startActivity(jumpStartGoogleIntent)
                }
         }
     }
     fun navToHomePage(){
         val homePageIntent = Intent(this,WeatherPortion::class.java)
         startActivity(homePageIntent)
         finish()

     }



 }

