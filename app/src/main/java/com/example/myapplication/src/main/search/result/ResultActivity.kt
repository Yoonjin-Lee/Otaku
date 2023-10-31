package com.example.myapplication.src.main.search.result

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.PostData
import com.example.myapplication.config.PostRVAdapter
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityResultBinding
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.search.SearchFragment

class ResultActivity : BaseActivity<ActivityResultBinding>(ActivityResultBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postList: ArrayList<PostData> = arrayListOf()
        val postRVAdapter = PostRVAdapter(postList, this)

        binding.resultSearch.setQuery(intent.getStringExtra("query"), false)
        binding.resultSearch.clearFocus()

        binding.resultSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 서버에서 정보 받아와서 넣기..?
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // 구현은 가능한데 어떻게 구현할 지 서버랑 이야기 해봐야 할 듯
                return true
            }
        })

        binding.resultRv.adapter = postRVAdapter
        binding.resultRv.layoutManager = LinearLayoutManager(this)

        binding.resultRv.addItemDecoration(RecyclerViewDecoration(5))

        binding.resultBtnClose.setOnClickListener {
            this.finish()
            MainActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frm, SearchFragment()).commitAllowingStateLoss()
        }
    }
}