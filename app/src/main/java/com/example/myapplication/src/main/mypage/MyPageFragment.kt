package com.example.myapplication.src.main.mypage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.config.HorizonRVDecoration
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.src.login.LoginActivity
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.mypage.certificate.CertificateActivity
import com.example.myapplication.src.main.mypage.manage.ManageActivity
import org.json.JSONObject
import retrofit2.Response

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page),
    MyPageFragmentView {
    private val admissionList = ArrayList<AdmissionData>()
    private val imageList = ArrayList<AdmissionData>()
    lateinit var admissionRVAdapter: AdmissionRVAdapter
    lateinit var presentRVAdapter: PresentRVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        admissionRVAdapter = AdmissionRVAdapter(admissionList, requireContext())
        presentRVAdapter = PresentRVAdapter(imageList, requireContext())

        MyPageService(this).tryGetAdmission()
        MyPageService(this).tryGetImage()

        binding.myPageRvAdmission.adapter = admissionRVAdapter
        binding.myPageRvAdmission.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.myPageRvAdmission.addItemDecoration(HorizonRVDecoration(7))

        binding.myPageRvPresent.adapter = presentRVAdapter
        binding.myPageRvPresent.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.myPageRvPresent.addItemDecoration(HorizonRVDecoration(7))

        binding.myPageBtnManage.setOnClickListener {
            val intent = Intent(context, ManageActivity::class.java)
            startActivity(intent)
        }

        binding.myPageBtnRegister.setOnClickListener {
            val intent = Intent(context, CertificateActivity::class.java)
            startActivity(intent)
        }

        binding.myPageBtnLogout.setOnClickListener {
            MyPageService(this).tryPostLogout()
        }
    }

    override fun onPostLogoutSuccess(response: String) {
        Log.d("key", ApplicationClass.sSharedPreferences.getString("accessToken", "") ?: "없음")
        if (response != null){
            val data = JSONObject(response).getInt("status")
            if (data == 200) {
                val intent = Intent(context, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }else{
                Toast.makeText(context, "통신 오류", Toast.LENGTH_LONG).show()
            }
        }else{
            Toast.makeText(context, "탈퇴 오류", Toast.LENGTH_LONG).show()
        }
    }

    override fun onPostLogoutFail(message: String) {
        Log.d("Retrofit2_Error", message)
    }

    override fun onGetAdmissionSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        Log.d("Retrofit", "$data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            admissionList.add(
                AdmissionData(
                    obj.getInt("eventId"),
                    obj.getString("name"),
                    obj.getString("openedDate")
                )
            )
        }
    }

    override fun onGetAdmissionFail(message: String) {
        Log.d("Retrofit2_Error", message)
    }

    override fun onGetImageSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        Log.d("Retrofit", "$data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            imageList.add(
                AdmissionData(
                    obj.getInt("eventId"),
                    obj.getString("name"),
                    obj.getString("openedDate")
                )
            )
        }
    }

    override fun onGetImageFail(message: String) {
        Log.d("Retrofit2_Error", message)
    }
}