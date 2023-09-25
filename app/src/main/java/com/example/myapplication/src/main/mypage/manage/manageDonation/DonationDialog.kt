package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.DialogDonationBinding

class DonationDialog(
//    id: Int
) : BaseActivity<DialogDonationBinding>(DialogDonationBinding::inflate){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val position = intent.getIntExtra("position", -1)
        Log.d("dialog_position", "${position}")

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.logoutYes.setOnClickListener {
            itemClickListener?.onClick(position)
            Log.d("dialog_position2", "${position}")
            this.finish()
        }

        binding.logoutNo.setOnClickListener {
            this.finish()
        }
    }

    interface OnItemClickListener{
        fun onClick(position : Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    private var itemClickListener: OnItemClickListener? = null
}