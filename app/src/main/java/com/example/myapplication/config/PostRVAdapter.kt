package com.example.myapplication.config

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ListPostBinding
import com.example.myapplication.src.main.search.post.PostActivity
import org.json.JSONObject

class PostRVAdapter(private val postList: ArrayList<PostData>, val context: Context) :
    RecyclerView.Adapter<PostRVAdapter.ViewHolder>(), PostDVAdapterView {
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
                if (data.heart) {
                    binding.postBtnHeart.setImageResource(R.drawable.favorite_border_black_48dp)
                    data.heart = false
                    PostAdapterService(this@PostRVAdapter).tryPostWishCancel(data.eventId)
                } else {
                    binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
                    data.heart = true
                    PostAdapterService(this@PostRVAdapter).tryPostWishList(data.eventId)
                }
            }
            if (data.heart) {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
            }
            if (data.donation) {
                binding.postBtnDonation.text = "모금 완료"
                binding.postBtnDonation.setBackgroundResource(R.drawable.btn_donated_shape)
                binding.postBtnDonation.setTextColor(Color.WHITE)
            }
            binding.postBtnDonation.setOnClickListener {
                // 게시물 화면으로 넘어가기
                val intent = Intent(context, PostActivity::class.java)
                intent.putExtra("eventId", data.eventId)
                intent.putExtra("donation", data.donation)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ListPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int = postList.size

    override fun onPostWishListSuccess(response: String) {
        val data = JSONObject(response).getString("message")
        Log.d("Retrofit", data)
    }

    override fun onPostWishListFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostWishCancelSuccess(response: String) {
        val data = JSONObject(response).getString("message")
        Log.d("Retrofit", data)
    }

    override fun onPostWishCancelFail(message: String) {
        Log.d("Retrofit", message)
    }
}