package com.example.hands_market

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nhn.android.naverlogin.OAuthLogin
import com.nhn.android.naverlogin.OAuthLoginHandler
import com.nhn.android.naverlogin.ui.view.OAuthLoginButton

class LoginActivity : AppCompatActivity() {

    companion object{
        var logStatus : Boolean = false //false:로그인 하지 않음 true:로그인 한 상태
        var isManager : Boolean = false //false:일반 사용자 true: 관리자
        fun checkLog(){}
    }

    lateinit var mOAuthLoginInstance : OAuthLogin
    lateinit var mContext: Context
    lateinit var buttonOAuthLoginImg : OAuthLoginButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  네이버 아이디로 로그인
        val naver_client_id = "vwdqBmG0qRv3hODfsHFb"
        val naver_client_secret = "NgGrxqcRen"
        val naver_client_name = "HandsMarket"

        mContext = this

        mOAuthLoginInstance = OAuthLogin.getInstance()
        mOAuthLoginInstance.init(mContext, naver_client_id, naver_client_secret, naver_client_name)
        buttonOAuthLoginImg = findViewById(R.id.buttonOAuthLoginImg)
        buttonOAuthLoginImg.setOAuthLoginHandler(mOAuthLoginHandler)

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.login_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    val mOAuthLoginHandler: OAuthLoginHandler = object : OAuthLoginHandler() {
        override fun run(success: Boolean) {
            if (success) {
//                val accessToken: String = mOAuthLoginModule.getAccessToken(baseContext)
//                val refreshToken: String = mOAuthLoginModule.getRefreshToken(baseContext)
//                val expiresAt: Long = mOAuthLoginModule.getExpiresAt(baseContext)
//                val tokenType: String = mOAuthLoginModule.getTokenType(baseContext)
//                var intent = Intent(this, )
            } else {
                val errorCode: String = mOAuthLoginInstance.getLastErrorCode(mContext).code
                val errorDesc = mOAuthLoginInstance.getLastErrorDesc(mContext)

                Toast.makeText(
                        baseContext, "errorCode:" + errorCode
                        + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT
                ).show()
            }
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
