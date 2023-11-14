package com.example.myapplication.src.main.search.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityPostBinding
import com.example.myapplication.src.main.home.add.addInfo.model.SubjectData
import com.example.myapplication.src.main.search.map.MapActivity
import com.example.myapplication.src.main.search.post.donate.DonateActivity
import com.example.myapplication.src.main.search.post.join.JoinActivity
import org.json.JSONObject

class PostActivity : BaseActivity<ActivityPostBinding>(ActivityPostBinding::inflate),
    PostActivityView {
    private var eventId = 0
    private var supportId: Int? = null
    private var image = ""
    private var isPublic = false
    private var address = ""
    private var subjectArray: ArrayList<SubjectData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventId = intent.getIntExtra("eventId", 0)
        PostService(this, this).tryGetEvent(eventId)
        PostService(this, this).tryGetAllSubjects()

        binding.postBtnClose.setOnClickListener {
            this.finish()
        }

        binding.postBtnDonation.setOnClickListener {
            if (binding.postBtnDonation.text == "후원하기") {
                val intent = Intent(this, DonateActivity::class.java)
                intent.putExtra("name", binding.postTxtTitle.text as String)
                intent.putExtra("xNickname", binding.postTxtName.text as String)
                intent.putExtra("xId", binding.postTxtId.text as String)
                intent.putExtra("image", image)
                intent.putExtra("subjectName", binding.postTxtMain.text as String)
                intent.putExtra("supportId", supportId)
                startActivity(intent)
            } else {
                if (isPublic) {
                    PostService(this, this).tryPostEventPublic(eventId)
                } else {
                    val intent = Intent(this, JoinActivity::class.java)
                    intent.putExtra("eventId", eventId)
                    intent.putExtra("isPublic", isPublic)
                    startActivity(intent)
                }
            }
        }
        binding.postBtnNotification.setOnClickListener {
            PostService(this, this).tryPostReport(eventId)
        }

        binding.postBtnMap.setOnClickListener {
            var subjectId = 0
            var subjectName = ""
            for (i in 0 until subjectArray.size) {
                if (subjectArray[i].name == binding.postTxtMain.text) {
                    subjectId = subjectArray[i].subjectId
                    subjectName = subjectArray[i].name
                }
            }
            val intent = Intent(this, MapActivity::class.java)
            intent.putExtra("address", address)
            intent.putExtra("subjectId", subjectId)
            intent.putExtra("title", subjectName)
            startActivity(intent)
        }
    }

    override fun onGetEventSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        eventId = data.getInt("eventId")
        image = data.getString("featuredImage")
        Glide.with(this)
            .asBitmap()
            .load(image.toUri())
            .into(binding.postImgMain)

        if (data.isNull("supportId")) {
            binding.postBtnDonation.text = "참여하기"
        } else {
            if (data.getString("status") == "UNDEFINED") {
                binding.postBtnDonation.text = "후원하기"
                supportId = data.getInt("supportId")
            }
        }

        if (!(data.isNull("currentAmount") && data.isNull("targetAmount"))) {
            val math = (data.getString("currentAmount").toFloat() / data.getString("targetAmount").toFloat()) * 100
            Log.d("Retrofit", "percentage: $math")
            binding.postTxtPercentage.text = binding.postTxtPercentage.text.toString() + math.toString()
        }

        binding.postTxtCategory.text = data.getString("category")
        binding.postTxtTitle.text = data.getString("name")
        binding.postTxtDate.text = data.getString("createdDate")
        binding.postTxtName.text = data.getString("xNickname")
        binding.postTxtId.text = data.getString("xId")
        binding.postTxtMain.text = data.getString("subjectName")
        if (data.isNull("currentAmount")){
            binding.postTxtDonatedMoney.text = "0"
        }else{
            binding.postTxtDonatedMoney.text = data.getString("currentAmount")
        }
        if (data.isNull("targetAmount")){
            binding.postTxtDonatedMoney.text = "0"
        }else{
            binding.postTxtDonationMoney.text = data.getString("targetAmount")
        }
        binding.postTxtContent.text = data.getString("description")
        binding.postTxtPosition.text = data.getString("address")
        var heart = data.getBoolean("wishList")
        binding.postBtnHeart.setOnClickListener {
            if (heart) {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_border_black_48dp)
                heart = false
                PostService(this, this).tryPostWishCancel(eventId)
            } else {
                binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
                heart = true
                PostService(this, this).tryPostWishList(eventId)
            }
        }
        if (heart) {
            binding.postBtnHeart.setImageResource(R.drawable.favorite_black_48dp)
        }
        isPublic = data.getBoolean("isPublic")
        address = data.getString("address")
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

    override fun onPostReportSuccess(response: String) {
        val data = JSONObject(response).getString("message")
        Log.d("Retrofit", data)
        showToast("게시물이 신고되었습니다.")
    }

    override fun onPostReportFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostEventPublicSuccess(response: String) {
        val data = JSONObject(response)
        if (data.getInt("status") == 409) {
            showToast("이미 이벤트 로그가 존재합니다.")
        }
        Log.d("Retrofit", "$data")
        showToast("등록되었습니다")
    }

    override fun onPostEventPublicFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onGetAllSubjectsSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        for (i in 0 until data.length()) {
            val obj = data.getJSONObject(i)
            subjectArray.add(
                SubjectData(
                    obj.getInt("subjectId"),
                    obj.getString("name")
                )
            )
        }
    }

    override fun onGetAllSubjectsFail(message: String) {
        Log.d("Retrofit", message)
    }
}