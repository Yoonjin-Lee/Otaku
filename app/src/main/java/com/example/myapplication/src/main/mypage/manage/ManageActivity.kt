package com.example.myapplication.src.main.mypage.manage

import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityManageBinding
import org.json.JSONObject

class ManageActivity : BaseActivity<ActivityManageBinding>(ActivityManageBinding::inflate),
    ManageActivityView {
    private val itemList = ArrayList<ManageData>()
    lateinit var adapter: ManageRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ManageRVAdapter(itemList, this)
        ManageService(this).tryGetHostEvents()

        binding.manageBtnClose.setOnClickListener {
            this.finish()
        }

        binding.manageRv.adapter = adapter
        binding.manageRv.layoutManager = LinearLayoutManager(this)

    }

    override fun onGetHostEventsSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        val array = data.getJSONArray("content")
        Log.d("Retrofit", "$array")
        for (i in 0 until array.length()){
            val obj = array.getJSONObject(i)
            itemList.add(
                ManageData(
                    obj.getInt("eventId"),
                    obj.getInt("supportId"),
                    obj.getString("featuredImage").toUri(),
                    obj.getString("name"),
                    obj.getString("xNickname"),
                    obj.getString("xId"),
                    obj.getString("subjectName")
                )
            )
        }
        adapter.notifyDataSetChanged()
    }

    override fun onGetHostEventsFail(message: String) {
        Log.d("Retrofit", message)
    }
}