package com.example.myapplication.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.src.main.home.add.AddActivity

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
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

        val postRVAdapter = PostRVAdapter(postList)

        binding.homeRv.adapter = postRVAdapter
        binding.homeRv.layoutManager = LinearLayoutManager(context)

        binding.homeRv.addItemDecoration(RecyclerViewDecoration(5))

        binding.homeBtnAdd.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }
    }
}