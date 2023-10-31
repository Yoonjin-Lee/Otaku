package com.example.myapplication.src.main.search.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityPostBinding
import com.example.myapplication.src.main.search.post.donate.DonateActivity
import org.json.JSONObject

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate),
    PostActivityView {
    private var eventId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PostService(this).tryGetEvent(eventId)

        binding.postBtnClose.setOnClickListener {
            this.finish()
        }

        binding.postBtnDonation.setOnClickListener {
            val intent = Intent(this, DonateActivity::class.java)
            intent.putExtra("eventId", eventId)
            startActivity(intent)
        }
    }

    override fun onGetEventSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        eventId = data.getInt("eventId")
        Glide.with(this)
            .load(data.getString("featuredImage").toUri())
            .into(binding.postImgMain)
        binding.postTxtCategory.text = data.getString("category")
        binding.postTxtTitle.text = data.getString("name")
        binding.postTxtDate.text = data.getString("createdDate")
        binding.postTxtName.text = data.getString("xNickname")
        binding.postTxtId.text = data.getString("xId")
        binding.postTxtMain.text = data.getString("subjectName")
        binding.postTxtDonatedMoney.text = data.getString("currentAmount")
        binding.postTxtDonationMoney.text = data.getString("targetAmount")
        binding.postTxtContent.text = data.getString("description")
        var heart = data.getBoolean("wishList")
        binding.postBtnHeart.setOnClickListener {
            if (heart) {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_border_black_48dp)
                heart = false
                PostService(this).tryPostWishCancel(eventId)
            } else {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
                heart = true
                PostService(this).tryPostWishList(eventId)
            }
        }
        if (heart) {
            binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
        }
        val status = data.getString("status") // 이거 어떻게 오는 지 물어봐야 함
    }

    override fun onGetEventFail(message: String) {
        Log.d("Retrofit", message)
    }
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