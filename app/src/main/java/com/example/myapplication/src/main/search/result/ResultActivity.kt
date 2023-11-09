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
//        binding.resultSearch.setQuery(query, false)
//        binding.resultSearch.clearFocus()
        binding.resultAuto.setText(query)
        ResultService(this).tryGetSubject(query!!)
        ResultService(this).tryGetAllSubjects()

//        binding.resultSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                // 서버에서 정보 받아와서 넣기..?
//                postList.clear()
//                ResultService(this@ResultActivity).tryGetSubject(query!!)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                // 구현은 가능한데 어떻게 구현할 지 서버랑 이야기 해봐야 할 듯
//                return true
//            }
//        })

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
            var donation = false
            if (obj.getString("status") != "PREPARATION"){
                donation = true
            }
            postList.add(
                PostData(
                    obj.getInt("eventId"),
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