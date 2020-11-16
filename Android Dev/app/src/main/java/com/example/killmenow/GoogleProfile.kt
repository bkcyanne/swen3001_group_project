package com.example.killmenow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.OptionalPendingResult
import com.google.android.gms.common.api.Result
import com.google.android.gms.common.api.Status
import com.squareup.picasso.Picasso

class GoogleProfile : AppCompatActivity() ,GoogleApiClient.OnConnectionFailedListener {
    lateinit var profImage:ImageView
    lateinit var googFullName: TextView
    lateinit var googEmail:TextView
    lateinit var googId:TextView
    lateinit var signOut:Button
    lateinit var jumpStartGoogleApiClient:GoogleApiClient
    lateinit var jumpStartGSO:GoogleSignInOptions
    lateinit var status :Status


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_profile)
        profImage = findViewById<ImageView>(R.id.GooglePic)
        googFullName = findViewById<TextView>(R.id.GoogleFullName)
        googEmail = findViewById<TextView>(R.id.GoogleEmail)
        googId = findViewById<TextView>(R.id.GoogleId)
        signOut = findViewById<Button>(R.id.SignOut)


        jumpStartGSO =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        jumpStartGoogleApiClient =
            GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(
                Auth.GOOGLE_SIGN_IN_API, jumpStartGSO
            ).build()

        signOut.setOnClickListener {

                Auth.GoogleSignInApi.signOut(jumpStartGoogleApiClient).setResultCallback {
                 fun onResult(@NonNull status:Status){

                    if(status.isSuccess){
                           navToProfile()

                    }else{

                        Toast.makeText(this,"Something went wrong Here within the logout",Toast.LENGTH_LONG).show()

                    }

            }
        }
    }


    }
    fun navToProfile(){


        val intent = Intent(this,profile::class.java)
        startActivity(intent)
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    fun handleSignInResult(result:GoogleSignInResult){
        if(result.isSuccess){

            val jumpStartSignInAccount:GoogleSignInAccount = result.signInAccount!!
            googFullName.setText(jumpStartSignInAccount.displayName)
            googEmail.setText(jumpStartSignInAccount.email)
            googId.setText(jumpStartSignInAccount.id)
            Picasso.get().load(jumpStartSignInAccount.photoUrl).placeholder(R.mipmap.ic_launcher).into(profImage)

        }else{
            Toast.makeText(this,"Something went wrong in the HandleSignIn",Toast.LENGTH_LONG).show()
            navToProfile()
        }
    }
    override fun onStart(){
        super.onStart()
       val opr :OptionalPendingResult<GoogleSignInResult> = Auth.GoogleSignInApi.silentSignIn(jumpStartGoogleApiClient)
        if(opr.isDone){

            val gResult :GoogleSignInResult=opr.get()
            handleSignInResult(gResult)
        }else{

            opr.setResultCallback{
                fun onResult(@NonNull result: GoogleSignInResult){

                    handleSignInResult(result)
                }

            }
        }

    }
}