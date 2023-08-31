package com.example.myapplication.src.main.home.add

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddBinding
import com.example.myapplication.src.main.home.picture.PictureActivity

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addBtnClose.setOnClickListener {
            this.finish()
        }

        binding.addBtnOpenEvent.setOnClickListener {

        }

        binding.addBtnClosedEvent.setOnClickListener {
            val intent = Intent(this, PictureActivity::class.java)
            startActivity(intent)
        }

    }
}