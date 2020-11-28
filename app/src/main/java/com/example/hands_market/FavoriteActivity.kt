package com.example.hands_market

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

lateinit var storeListFragment: StoreListFragment
lateinit var goodsListFragment: GoodsListFragment

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val bundle: Bundle = Bundle()
        bundle.putString("type","favorite")


        // 즐겨찾기 조회 (상점) 버튼
        val favoriteStoreBtn = findViewById<Button>(R.id.favoriteStoreBtn6)
        favoriteStoreBtn.setOnClickListener{
            storeListFragment = StoreListFragment();
            storeListFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.favorite_activity_frame_layout, storeListFragment).commit()

        }

        // 즐겨찾기 조회 (상품) 버튼
        val favoriteGoodsBtn = findViewById<Button>(R.id.favoriteGoodsBtn)
        favoriteGoodsBtn.setOnClickListener{
            goodsListFragment = GoodsListFragment();
            goodsListFragment.arguments = bundle
            supportFragmentManager.beginTransaction().replace(R.id.favorite_activity_frame_layout, goodsListFragment).commit()
        }

        // bottom navigation 선언
        val navigationBar = findViewById<BottomNavigationView>(R.id.favorite_navigation)
        navigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
            R.id.navigation_mypage -> {
                val intent = Intent(this,MypageActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }
}
