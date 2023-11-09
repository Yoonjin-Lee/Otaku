package com.example.myapplication.src.main.mypage.manage.manageParticipate

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityManageParticipateBinding
import org.json.JSONObject

class ManageParticipateActivity :
    BaseActivity<ActivityManageParticipateBinding>(ActivityManageParticipateBinding::inflate),
    ManageParticipateActivityView {
    private val itemList = ArrayList<ParticipateData>()
    val adapter = ParticipateRVAdapter(itemList, this)
    private var p : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ManageParticipateService(this, this).tryGetUsers(intent.getIntExtra("eventId", 0))

        binding.manageParticipateBtnClose.setOnClickListener {
            this.finish()
        }

        binding.manageParticipateRv.adapter = adapter
        binding.manageParticipateRv.layoutManager = LinearLayoutManager(this)
        binding.manageParticipateRv.addItemDecoration(RecyclerViewDecoration(8))
        adapter.setItemClickListener(object: ParticipateRVAdapter.OnItemClickListener{
            override fun onClick(v: View, position: Int) {
                p = position
                ManageParticipateService(this@ManageParticipateActivity, this@ManageParticipateActivity).tryPostUser(
                    itemList[position].approvalId
                )
            }
        })
    }

    override fun onGetUsersSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            if (obj.getString("status") != "APPROVED"){
                itemList.add(
                    ParticipateData(
                        obj.getInt("approvalId"),
                        obj.getString("xNickname"),
                        obj.getString("xId")
                    )
                )
            }
        }
        adapter.notifyDataSetChanged()
    }

    override fun onGetUsersFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostUserSuccess(response: String) {
        itemList.remove(itemList[p])
        adapter.notifyItemRemoved(p)
        showToast("승인되었습니다")
    }

    override fun onPostUserFail(message: String) {
        Log.d("Retrofit", message)
    }
}