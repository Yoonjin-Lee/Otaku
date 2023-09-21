package com.example.myapplication.src.main.mypage.manage.manageParticipate

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityManageParticipateBinding

class ManageParticipateActivity : BaseActivity<ActivityManageParticipateBinding>(ActivityManageParticipateBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.manageParticipateBtnClose.setOnClickListener {
            this.finish()
        }

        val itemList = ArrayList<ParticipateData>()
        itemList.apply {
            add(ParticipateData("이윤진", "@rainy_day"))
        }

        val adapter = ParticipateRVAdapter(itemList, this)
        binding.manageParticipateRv.adapter = adapter
        binding.manageParticipateRv.layoutManager = LinearLayoutManager(this)
        binding.manageParticipateRv.addItemDecoration(RecyclerViewDecoration(8))
    }
}