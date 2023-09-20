package com.example.myapplication.src.main.mypage.manage

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityManageBinding

class ManageActivity : BaseActivity<ActivityManageBinding>(ActivityManageBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.manageBtnClose.setOnClickListener {
            this.finish()
        }

        val itemList = ArrayList<ManageData>()

        itemList.apply {
            add(ManageData(R.drawable.ic_launcher_background, "고조 사토루 생일카페", "이윤진", "geto", "주술회전 고조 사토루"))
        }

        val adapter = ManageRVAdapter(itemList, this)
        binding.manageRv.adapter = adapter
        binding.manageRv.layoutManager = LinearLayoutManager(this)

    }
}