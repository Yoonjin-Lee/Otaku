package com.example.myapplication.src.main.search.post

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityPostBinding
import com.example.myapplication.src.main.search.post.donate.DonateActivity

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.postBtnClose.setOnClickListener {
            this.finish()
        }

        binding.postBtnDonation.setOnClickListener {
            val intent = Intent(this, DonateActivity::class.java)
            startActivity(intent)
        }
    }
}