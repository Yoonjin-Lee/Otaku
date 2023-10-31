package com.example.myapplication.src.main.mypage.manage.manageSelect

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityManageSelectBinding
import com.example.myapplication.src.main.mypage.manage.manageDonation.ManageDonationActivity
import com.example.myapplication.src.main.mypage.manage.manageParticipate.ManageParticipateActivity

class ManageSelectActivity : BaseActivity<ActivityManageSelectBinding>(ActivityManageSelectBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventId = intent.getIntExtra("eventId", 0)
        val supportId = intent.getIntExtra("supportId", 0)

        binding.manageSelectBtnClose.setOnClickListener {
            this.finish()
        }

        binding.manageSelectBtnDonation.setOnClickListener {
            val intent = Intent(this, ManageDonationActivity::class.java)
            intent.putExtra("supportId", supportId)
            startActivity(intent)
        }

        binding.manageSelectBtnParticipate.setOnClickListener {
            val intent = Intent(this, ManageParticipateActivity::class.java)
            intent.putExtra("eventId", eventId)
            startActivity(intent)
        }

    }
}