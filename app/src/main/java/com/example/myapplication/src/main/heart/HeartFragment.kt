package com.example.myapplication.src.main.heart

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.FragmentHeartBinding

class HeartFragment : BaseFragment<FragmentHeartBinding>(FragmentHeartBinding::bind, R.layout.fragment_heart) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val postList: ArrayList<PostData> = arrayListOf()

        postList.apply {
            add(
                PostData(
                    R.drawable.ic_launcher_background,
                    "제목",
                    "이름",
                    "아이디",
                    "고죠 사토루",
                    true,
                    true
                )
            )
        }

        val postRVAdapter = PostRVAdapter(postList, requireContext())

        binding.heartRv.adapter = postRVAdapter
        binding.heartRv.layoutManager = LinearLayoutManager(context)

        binding.heartRv.addItemDecoration(RecyclerViewDecoration(5))
    }
}