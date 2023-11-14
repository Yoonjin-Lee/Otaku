package com.example.myapplication.src.main.heart

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
import com.example.myapplication.databinding.FragmentHeartBinding
import org.json.JSONObject

class HeartFragment :
    BaseFragment<FragmentHeartBinding>(FragmentHeartBinding::bind, R.layout.fragment_heart),
    HeartFragmentView {
    private val postList: ArrayList<PostData> = arrayListOf()
    lateinit var postRVAdapter: PostRVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        HeartService(this).tryGetWishEvents()

        postRVAdapter = PostRVAdapter(postList, requireContext())

        binding.heartRv.adapter = postRVAdapter
        binding.heartRv.layoutManager = LinearLayoutManager(context)

        binding.heartRv.addItemDecoration(RecyclerViewDecoration(5))
    }

    override fun onGetWishEventsSuccess(response: String) {
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
                    obj.getInt("eventId"),
                    obj.getString("featuredImage"),
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

    override fun onGetWishEventsFail(message: String) {
        Log.d("Retrofit", "wish events data 가져오기 실패")
    }
}