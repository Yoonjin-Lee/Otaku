package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityManageDonationBinding

class ManageDonationActivity : BaseActivity<ActivityManageDonationBinding>(ActivityManageDonationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.manageDonationBtnClose.setOnClickListener {
            this.finish()
        }

        val itemList = ArrayList<DonationData>()

        itemList.apply {
            add(DonationData("이윤진", "10000"))
        }

        val adapter = DonationRVAdapter(itemList, this)
        binding.manageDonationRv.adapter = adapter
        binding.manageDonationRv.layoutManager = LinearLayoutManager(this)
        binding.manageDonationRv.addItemDecoration(RecyclerViewDecoration(8))
    }
}