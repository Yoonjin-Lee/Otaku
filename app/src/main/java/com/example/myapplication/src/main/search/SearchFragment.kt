package com.example.myapplication.src.main.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.src.main.search.category.CategoryActivity
import com.example.myapplication.src.main.search.result.ResultActivity

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(context, ResultActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.searchBtnIdol.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnIdol.text)
            startActivity(intent)
        }

        binding.searchBtnActor.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnActor.text)
            startActivity(intent)
        }

        binding.searchBtnVirtual.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnVirtual.text)
            startActivity(intent)
        }

        binding.searchBtnAnime.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnAnime.text)
            startActivity(intent)
        }

        binding.searchBtnSports.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnSports.text)
            startActivity(intent)
        }

        binding.searchBtnEtc.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("title", binding.searchBtnEtc.text)
            startActivity(intent)
        }
    }
}