package com.example.myapplication.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.src.main.heart.HeartFragment
import com.example.myapplication.src.main.home.HomeFragment
import com.example.myapplication.src.main.mypage.MyPageFragment
import com.example.myapplication.src.main.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBnb.run {
            setOnItemSelectedListener {
                when(it.itemId){
                    R.id.main_home -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.main_search -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, SearchFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.main_heart -> {
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, HeartFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.main_my_page ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(binding.mainFrm.id, MyPageFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId = R.id.main_home
        }
    }
}