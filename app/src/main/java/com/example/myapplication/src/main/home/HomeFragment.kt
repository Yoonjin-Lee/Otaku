package com.example.myapplication.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.src.main.home.add.AddActivity
import org.json.JSONObject

class HomeFragment :
    BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), HomeFragmentView {
    private val postList: ArrayList<PostData> = arrayListOf()
    lateinit var postRVAdapter: PostRVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postRVAdapter = PostRVAdapter(postList, requireContext())
        HomeService(this).tryGetEvents()

        binding.homeRv.adapter = postRVAdapter
        binding.homeRv.layoutManager = LinearLayoutManager(context)

        binding.homeRv.addItemDecoration(RecyclerViewDecoration(5))

        binding.homeBtnAdd.setOnClickListener {
            val intent = Intent(context, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetEventsSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        val array = data.getJSONArray("content")
        Log.d("Retrofit", "$array")
        for(i in 0 until array.length()){
            val obj = array.getJSONObject(i)
            var donation = false
            if (obj.getString("status") != "PREPARATION"){
                donation = true
            }
            postList.add(
                PostData(
                    obj.getString("featuredImage").toUri(),
                    obj.getString("name"),
                    obj.getString("xNickname"),
                    obj.getString("xId"),
                    obj.getString("subjectName"),
                    obj.getBoolean("wishList"),
                    donation
                )
            )
        }
        postRVAdapter.notifyDataSetChanged()
    }

    override fun onGetEventsFail(message: String) {
        Log.d("Retrofit", "events data 가져 오기 실패")
    }
}