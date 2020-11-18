package com.example.killmenow

import android.content.Intent
import android.hardware.biometrics.BiometricPrompt
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import android.widget.Toast
<<<<<<< HEAD
import androidx.annotation.RequiresApi
=======
import androidx.lifecycle.ViewModelProvider
import com.example.database.UserViewModel
>>>>>>> 35d1be13a26539da485f5bc5faf0a46ead2112ac
import com.google.android.gms.common.SignInButton
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.ConnectionResult


import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.annotations.NonNls
import java.lang.Thread.sleep


 class MainActivity() : AppCompatActivity(),GoogleApiClient.OnConnectionFailedListener{

    lateinit var uN: String
    lateinit var pW: String
    lateinit var uNInput: EditText
    lateinit var pWInput: EditText
    private var counter: Int = 2
    lateinit var jumpStartSignInButton: Button
    lateinit var jumpStartGoogleSignInResult :GoogleSignInResult
     lateinit var createAccUserViewModel: UserViewModel

     private var cancellationSignal: CancellationSignal? = null
     private val authenticationCallback: BiometricPrompt.AuthenticationCallback
     get() =
         @RequiresApi(Build.VERSION_CODES.P)
         object : BiometricPrompt.AuthenticationCallback(){
             override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                 super.onAuthenticationError(errorCode, errString)
                 notifyUser("Authentication error: $errString")
             }

             override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                 super.onAuthenticationSucceeded(result)
                 notifyUser("Authentication successful!")
                 //startActivity(Intent(this@MainActivity, ))
             }
         }

    override fun onCreate(savedInstanceState: Bundle?) {

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

                if (uN == "AdminCorey" && pW == "tasteepatty") {

                    Toast.makeText(this, "Welcome back $uN!", Toast.LENGTH_SHORT).show()
                    sleep(3000)


                    val intent = Intent(this, accCreationPage::class.java)
                    startActivity(intent)

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
            val intent = Intent(this, accCreationPage::class.java)
            startActivity(intent)

        }

        val jumpStartGSO = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        val jumpStartGoogleApiClient = GoogleApiClient.Builder(this).enableAutoManage(this,this).addApi(
            Auth.GOOGLE_SIGN_IN_API,jumpStartGSO).build()

        jumpStartSignInButton=findViewById<Button>(R.id.GoogleSignUp)
        jumpStartSignInButton.setOnClickListener{


            val jumpStartGoogleIntent =Auth.GoogleSignInApi.getSignInIntent(jumpStartGoogleApiClient)
            startActivityForResult(jumpStartGoogleIntent,SIGN_IN)
        }
    }




     override fun onConnectionFailed(p0: ConnectionResult) {
         TODO("Not yet implemented")
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)

            if(requestCode == SIGN_IN){

                jumpStartGoogleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data)!!
                if(jumpStartGoogleSignInResult.isSuccess()){
                    Toast.makeText(this,"Successfull up to this lane",Toast.LENGTH_SHORT).show()
                    sleep(3000)

                   val jumpStartGoogleIntent = Intent(this,GoogleProfile::class.java)
                    startActivity(jumpStartGoogleIntent)
                }
         }
     }

     //Andryck's attempt at a fingerprint sensor starts here

     /*This function displays a message to the user*/
     private fun notifyUser(message: String){
         Toast.makeText(this, message,Toast.LENGTH_SHORT).show()
     }

 }

