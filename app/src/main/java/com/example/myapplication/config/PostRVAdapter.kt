package com.example.myapplication.config

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ListPostBinding
import com.example.myapplication.src.main.search.post.PostActivity

class PostRVAdapter(private val postList : ArrayList<PostData>, val context : Context) : RecyclerView.Adapter<PostRVAdapter.ViewHolder>(){
    inner class ViewHolder(private val binding: ListPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PostData) {
            Glide.with(context)
                .load(data.image)
                .into(binding.postImage)
            binding.postTitle.text = data.title
            binding.postName.text = data.name
            binding.postId.text = data.id
            binding.postMain.text = data.main
            binding.postBtnHeart.setOnClickListener {
                if (data.heart){
                    binding.postBtnHeart.setImageResource(R.drawable.favorite_border_black_48dp)
                    data.heart = false
                } else{
                    binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
                    data.heart = true
                }
            }
            if (data.heart) {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
            }
            if (data.donation) {
                binding.postBtnDonation.text = "모집 완료"
                binding.postBtnDonation.setBackgroundResource(R.drawable.btn_donated_shape)
                binding.postBtnDonation.setTextColor(Color.WHITE)
                binding.postBtnDonation.setOnClickListener {
                    // 게시물 화면으로 넘어가기
                    val intent = Intent(context, PostActivity::class.java)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size
}