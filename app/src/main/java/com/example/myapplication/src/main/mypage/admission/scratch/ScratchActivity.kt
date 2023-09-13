package com.example.myapplication.src.main.mypage.admission.scratch

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityScratchBinding

class ScratchActivity : BaseActivity<ActivityScratchBinding>(ActivityScratchBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.admissionBtnClose.setOnClickListener {
            this.finish()
        }

        binding.scratchBtnSave.setOnClickListener {

        }

        // 이미지 받아서 로드
//        Glide.with(this)
//            .load(URI)
//            .into(binding.scratchImgPresent)

    }
}