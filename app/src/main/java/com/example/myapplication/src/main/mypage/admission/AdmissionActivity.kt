package com.example.myapplication.src.main.mypage.admission

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAdmissionBinding
import com.example.myapplication.src.main.mypage.AdmissionData
import com.example.myapplication.src.main.mypage.AdmissionRVAdapter
import com.example.myapplication.src.main.mypage.admission.scratch.ScratchActivity

class AdmissionActivity : BaseActivity<ActivityAdmissionBinding>(ActivityAdmissionBinding::inflate)  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.admissionBtnClose.setOnClickListener {
            this.finish()
        }

        binding.admissionBtnInput.setOnClickListener {
            // 입장코드가 일치한다면 넘어가기
            // 아니면 toast
            val intent = Intent(this, ScratchActivity::class.java)
            startActivity(intent)
        }
    }
}