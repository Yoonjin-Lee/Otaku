package com.example.myapplication.src.main.heart

import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.config.BaseFragment
import com.example.myapplication.databinding.FragmentHeartBinding

class HeartFragment : BaseFragment<FragmentHeartBinding>(FragmentHeartBinding::bind, R.layout.fragment_heart) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}