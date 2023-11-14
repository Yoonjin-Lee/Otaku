package com.example.myapplication.config

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(private val inflate: (LayoutInflater) -> B) :
    AppCompatActivity(){
        protected lateinit var binding : B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 여기 밑에 공통적으로 들어가는 것들 적어주면 됨.
    fun showToast(m : String){
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show()
    }
}