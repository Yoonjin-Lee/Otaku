package com.example.myapplication.src.main.mypage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.FragmentMyPageBinding
import com.example.myapplication.src.main.mypage.certificate.CertificateActivity
import com.example.myapplication.src.main.mypage.manage.ManageActivity

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemList = ArrayList<AdmissionData>()

        itemList.apply {
            add(AdmissionData("이윤진 탄생일", "2023-03-13"))
            add(AdmissionData("이윤진 탄생일", "2023-03-13"))
        }

        val admissionRVAdapter = AdmissionRVAdapter(itemList, requireContext())
        binding.myPageRvAdmission.adapter = admissionRVAdapter
        binding.myPageRvAdmission.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.myPageRvAdmission.addItemDecoration(HorizonRVDecoration(7))

        val presentRVAdapter = PresentRVAdapter(itemList, requireContext())
        binding.myPageRvPresent.adapter = presentRVAdapter
        binding.myPageRvPresent.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.myPageRvPresent.addItemDecoration(HorizonRVDecoration(7))

        binding.myPageBtnManage.setOnClickListener {
            val intent = Intent(context, ManageActivity::class.java)
            startActivity(intent)
        }

        binding.myPageBtnRegister.setOnClickListener {
            val intent = Intent(context, CertificateActivity::class.java)
            startActivity(intent)
        }
    }
}