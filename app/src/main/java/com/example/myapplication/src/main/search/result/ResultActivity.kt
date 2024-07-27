package com.example.myapplication.src.main.search.result

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityResultBinding
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.search.SearchFragment
import org.json.JSONObject

class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate),
    ResultActivityView {
    private val postList: ArrayList<PostData> = arrayListOf()
    val postRVAdapter = PostRVAdapter(postList, this)
    private val subjectArray : ArrayList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val query = intent.getStringExtra("query")
        binding.resultAuto.setText(query)
        ResultService(this).tryGetSubject(query!!)
        ResultService(this).tryGetAllSubjects()

        binding.resultBtnSearch.setOnClickListener {
            postList.clear()
            ResultService(this@ResultActivity).tryGetSubject(binding.resultAuto.text.toString())
        }

        val autoCompleteTextView = binding.resultAuto

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, subjectArray)
        autoCompleteTextView.setAdapter(adapter)


        binding.resultRv.adapter = postRVAdapter
        binding.resultRv.layoutManager = LinearLayoutManager(this)

        binding.resultRv.addItemDecoration(RecyclerViewDecoration(5))

        binding.resultBtnClose.setOnClickListener {
            this.finish()
            MainActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, SearchFragment()).commitAllowingStateLoss()
        }
    }


    override fun onGetSubjectSuccess(response: String) {
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

    override fun onGetSubjectFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onGetAllSubjectsSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            subjectArray.add(
                obj.getString("name")
            )
        }
    }

    override fun onGetAllSubjectsFail(message: String) {
        Log.d("Retrofit", message)
    }
}