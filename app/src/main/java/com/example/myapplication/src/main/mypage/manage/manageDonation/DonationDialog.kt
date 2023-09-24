package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.DialogDonationBinding

class DonationDialog(
//    id: Int
) : BaseActivity<DialogDonationBinding>(DialogDonationBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.logoutYes.setOnClickListener {
            this.finish()
        }

        binding.logoutNo.setOnClickListener {
            this.finish()
        }
    }
}