package com.example.myapplication.src.main.search.category

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCategoryBinding
import com.example.myapplication.src.main.search.map.MapActivity

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.categoryTxtTitle.text = intent.getStringExtra("title")

        binding.categoryBtnClose.setOnClickListener {
            this.finish()
        }

        val cList : ArrayList<String> = arrayListOf()
        cList.apply {
            add("주술회전")
            add("도쿄구울")
        }
        val adapter = CategoryRVAdapter(cList)

        binding.categoryRv.adapter = adapter
        binding.categoryRv.layoutManager = LinearLayoutManager(this)

        adapter.setCategoryClickListener(object : CategoryRVAdapter.OnCategoryClickListener{
            override fun onClick(v: View, position: Int, title : String) {
                val intent = Intent(this@CategoryActivity, MapActivity::class.java)
                intent.putExtra("title", title)
                startActivity(intent)
            }
        })
    }
}