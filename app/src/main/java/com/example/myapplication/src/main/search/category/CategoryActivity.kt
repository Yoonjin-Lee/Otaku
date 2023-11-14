package com.example.myapplication.src.main.search.category

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCategoryBinding
import com.example.myapplication.src.main.search.map.MapActivity
import org.json.JSONObject

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate),
    CategoryActivityView {
    private val cList: ArrayList<CData> = arrayListOf()
    lateinit var adapter: CategoryRVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CategoryRVAdapter(cList)
        binding.categoryTxtTitle.text = intent.getStringExtra("title")

        CategoryService(this).tryGetCategory(intent.getStringExtra("title")!!)

        binding.categoryBtnClose.setOnClickListener {
            this.finish()
        }

        binding.categoryRv.adapter = adapter
        binding.categoryRv.layoutManager = LinearLayoutManager(this)

        adapter.setCategoryClickListener(object : CategoryRVAdapter.OnCategoryClickListener {
            override fun onClick(v: View, position: Int, data: CData) {
                val intent = Intent(this@CategoryActivity, MapActivity::class.java)
                intent.putExtra("title", data.title)
                intent.putExtra("subjectId", data.id)
                startActivity(intent)
            }
        })
    }

    override fun onGetCategorySuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        val array = data.getJSONArray("content")
        for(i in 0 until array.length()){
            val obj = array.getJSONObject(i)
            cList.add(
                CData(
                    obj.getString("name"),
                    obj.getInt("subjectId")
                )
            )
        }
        adapter.notifyDataSetChanged()
    }

    override fun onGetCategoryFail(message: String) {
        Log.d("Retrofit", message)
    }
}