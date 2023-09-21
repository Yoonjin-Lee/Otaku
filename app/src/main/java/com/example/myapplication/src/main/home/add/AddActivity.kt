package com.example.myapplication.src.main.home.add

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddBinding
import com.example.myapplication.src.main.home.add.addInfo.AddInfoActivity
import com.example.myapplication.src.main.home.add.picture.PictureActivity

class AddActivity : BaseActivity<ActivityAddBinding>(ActivityAddBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addBtnClose.setOnClickListener {
            this.finish()
        }

        binding.addBtnOpenEvent.setOnClickListener {
            val intent = Intent(this, AddInfoActivity::class.java)
            startActivity(intent)
        }

        binding.addBtnClosedEvent.setOnClickListener {
            val intent = Intent(this, PictureActivity::class.java)
            startActivity(intent)
        }

    }
}