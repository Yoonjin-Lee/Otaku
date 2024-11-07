package com.example.myapplication.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.*
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
        HomeService(this, requireContext()).tryGetEvents()

        binding.homeRv.adapter = postRVAdapter
        binding.homeRv.layoutManager = LinearLayoutManager(context)

        binding.homeRv.addItemDecoration(RecyclerViewDecoration(5))

        binding.homeBtnAdd.setOnClickListener {
            if (ApplicationClass.sSharedPreferences.getBoolean("role", false)){
                val intent = Intent(context, AddActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(context, "개최자만 등록할 수 있습니다", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onGetEventsSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        val array = data.getJSONArray("content")
        Log.d("Retrofit", "$array")
        for(i in 0 until array.length()){
            val obj = array.getJSONObject(i)
            var donation = true
            if (obj.getString("status") == "UNDEFINED"){
                donation = false
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

    override fun onGetEventsFail(message: String) {
        Log.d("Retrofit", "events data 가져 오기 실패")
    }
}