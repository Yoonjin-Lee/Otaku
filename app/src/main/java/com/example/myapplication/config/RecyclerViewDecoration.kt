package com.example.myapplication.config

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

//아이템 간 간격 조절
class RecyclerViewDecoration(private val divHeight: Int) : ItemDecoration() {
//    fun getItemOffsets(
//        outRect: Rect,
//        view: View?,
//        parent: RecyclerView?,
//        state: RecyclerView.State?
//    ) {
//        super.getItemOffsets(outRect, view!!, parent!!, state!!)
//        outRect.top = divHeight
//    }
}