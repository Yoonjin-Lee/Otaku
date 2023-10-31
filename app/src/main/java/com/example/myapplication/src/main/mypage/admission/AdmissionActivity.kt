package com.example.myapplication.src.main.mypage.admission

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAdmissionBinding
import com.example.myapplication.src.main.mypage.admission.scratch.ScratchActivity
import org.json.JSONObject

class AdmissionActivity : BaseActivity<ActivityAdmissionBinding>(ActivityAdmissionBinding::inflate),
    AdmissionActivityView {
    private var eventId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AdmissionService(this).tryGetAdmission(intent.getIntExtra("eventId", 0))

        binding.admissionBtnClose.setOnClickListener {
            this.finish()
        }

        binding.admissionBtnInput.setOnClickListener {
            // 입장코드가 일치한다면 넘어가기
            // 아니면 toast
            AdmissionService(this).tryPostCode(intent.getIntExtra("eventId", 0), binding.admissionEditCode.text.toString().toInt())
        }
    }

    override fun onGetAdmissionSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        binding.admissionTxtTicketTitle.text = data.getString("name")
        binding.admissionTxtTicketDate.text = data.getString("openedDate")
        binding.admissionTxtTicketName.text = data.getString("xNickname")
        eventId = data.getInt("eventId")
    }

    override fun onGetAdmissionFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostCodeSuccess(response: String) {
        Log.d("Retrofit", response)
        val intent = Intent(this, ScratchActivity::class.java)
        intent.putExtra("eventId", eventId)
        startActivity(intent)
    }

    override fun onPostCodeFail(message: String) {
        Log.d("Retrofit", message)
    }
}