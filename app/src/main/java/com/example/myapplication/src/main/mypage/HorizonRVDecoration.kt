package com.example.myapplication.src.main.mypage

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HorizonRVDecoration(private val divHeight: Int) : ItemDecoration(){
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = divHeight
    }
}
