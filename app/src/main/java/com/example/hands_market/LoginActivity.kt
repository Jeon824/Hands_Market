package com.example.hands_market

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Base64
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class LoginActivity : AppCompatActivity() {

    // Google Sign-In Methods
    var googleSignInClient : GoogleSignInClient? = null
    val RC_SIGN_IN = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Google Sign-In Methods
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        btn_google_login.setOnClickListener {
            var signInIntent = googleSignInClient?.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

        // bottom navigation 선언
        val login_navigation = findViewById<BottomNavigationView>(R.id.login_navigation)
        login_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    // Move to MainActivity
    fun fun_MoveNextPage(){
        var currentUser = FirebaseAuth.getInstance().currentUser
        if(currentUser != null){
            startActivity(Intent(this, MainActivity::class.java))
            this.finish()
        }
    }

    // Google Sign-In Methods
    fun fun_FirebaseAuthWithGoogle(acct : GoogleSignInAccount?){
        var credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener { task ->
            if(task.isSuccessful){
                fun_MoveNextPage()
            }
        }
    }

    //Override
    override fun onResume() {
        super.onResume()
        fun_MoveNextPage()  //Automatic Login
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Google Sign-In Methods
        if(requestCode == RC_SIGN_IN){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try{
                val account = task.getResult(ApiException::class.java)
                fun_FirebaseAuthWithGoogle(account)
            }
            catch (e : ApiException){

            }
        }
        else{

        }
    }

    // bottom navigation 버튼 출력 함수
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_favorite -> {
                val intent = Intent(this,FavoriteActivity::class.java)
                startActivity(intent)
            }
            R.id.navigation_log -> {
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
