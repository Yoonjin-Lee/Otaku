package com.example.myapplication.src.main.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.src.main.search.category.CategoryActivity
import com.example.myapplication.src.main.search.result.ResultActivity
import org.json.JSONObject

class SearchFragment :
    BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search),
    SearchFragmentView {
    private val subjectArray : ArrayList<String> = arrayListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.searchSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                val intent = Intent(context, ResultActivity::class.java)
//                intent.putExtra("query", query)
//                startActivity(intent)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })

        val autoCompleteTextView = binding.searchSearch

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, subjectArray)
        autoCompleteTextView.setAdapter(adapter)

        binding.resultBtnSearch.setOnClickListener {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra("query", binding.searchSearch.text.toString())
            startActivity(intent)
        }

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