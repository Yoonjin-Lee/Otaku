package com.example.myapplication.src.main.search.post.donate

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDonateBinding
import com.example.myapplication.src.main.mypage.certificate.UriUtil
import com.example.myapplication.src.main.search.post.donate.model.SupportData
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class DonateActivity : BaseActivity<ActivityDonateBinding>(ActivityDonateBinding::inflate),
    DonateActivityView {
    private var image: Uri? = null

    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            image = uri

            Glide.with(this)
                .load(uri)
                .into(binding.donateImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val supportId = intent.getIntExtra("supportId", 0)
        Log.d("Retrofit", "supportId : $supportId")
        DonateService(this).tryGetSupport(supportId)

        binding.postTitle.text = intent.getStringExtra("name")
        binding.postMain.text = intent.getStringExtra("subjectId")
        binding.postId.text = intent.getStringExtra("xId")
        binding.postName.text = intent.getStringExtra("xNickname")
        Glide.with(this)
            .load(intent.getStringExtra("image")?.toUri())
            .into(binding.postImage)

        binding.donateBtnClose.setOnClickListener {
            this.finish()
        }

        binding.donateBtnFinish.setOnClickListener {
            if (binding.donateImgShow.drawable != null && binding.donateEditPrice.text.isNotEmpty()) {
                val file = UriUtil.toFile(this, image!!)
                Log.d("Retrofit", "uri: ${file.absolutePath}")
                val body = file.absolutePath.toRequestBody("multipart/form-data".toMediaType())
                val part = MultipartBody.Part.createFormData("perksImageFile", file.name, body)
                DonateService(this).tryPostSupport(
                    supportId,
                    part,
                    SupportData(
                        "bank",
                        ApplicationClass.sSharedPreferences.getString("user", "USER") as String,
                        binding.donateEditPrice.text.toString().toInt()
                    )
                )
                this.finish()
            } else {
                showToast("사진 등록 및 금액 입력을 해주세요")
            }
        }

        binding.donateBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }

    override fun onGetSupportSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        binding.donateTxtAccount.text = data.getString("accountAddress")
        binding.donateTxtBank.text = data.getString("bank")
        binding.donateTxtBankOwner.text = data.getString("accountHolder")
    }

    override fun onGetSupportFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostSupportSuccess(response: String) {

    }

    override fun onPostSupportFail(message: String) {
        Log.d("Retrofit", message)
    }
}