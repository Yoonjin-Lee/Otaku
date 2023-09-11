package com.example.myapplication.src.main.search.post.join

import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityJoinBinding

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.joinBtnClose.setOnClickListener {
            this.finish()
        }

        binding.joinBtnJoin.setOnClickListener {
            this.finish()
        }

        val name = binding.joinAddEditTwtName.text
        val id = binding.joinAddEditTwtId.text

    }
}