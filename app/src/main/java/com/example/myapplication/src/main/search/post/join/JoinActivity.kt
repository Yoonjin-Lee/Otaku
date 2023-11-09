package com.example.myapplication.src.main.search.post.join

import android.os.Bundle
import android.util.Log
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityJoinBinding
import com.example.myapplication.src.main.search.post.join.model.JoinData
import com.example.myapplication.src.main.search.post.join.model.RequestData
import org.json.JSONObject

class JoinActivity : BaseActivity<ActivityJoinBinding>(ActivityJoinBinding::inflate),
    JoinActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val eventId = intent.getIntExtra("eventId", 0)

        binding.joinBtnClose.setOnClickListener {
            this.finish()
        }

        binding.joinBtnJoin.setOnClickListener {
            val name = binding.joinAddEditTwtName.text.toString()
            val id = binding.joinAddEditTwtId.text.toString()
            if (name.isNotEmpty() &&
                id.isNotEmpty()
            ) {
                val jsonObject = JSONObject()
                jsonObject.put(
                    "request",
                    JoinData(
                        name,
                        id
                    )
                )
                JoinService(this).tryPostEvent(
                    eventId,
                    JoinData(
                        name, id
                    )
                )
            } else {
                showToast("닉네임과 아이디를 입력해주세요")
            }
        }

    }

    override fun onPostEventSuccess(response: String) {
        showToast("등록되었습니다. 개최자 승인 후 참여 가능합니다")
        binding.joinBtnJoin.isEnabled = false
        this.finish()
    }

    override fun onPostEventFail(message: String) {
        Log.d("Retrofit", message)
    }
}