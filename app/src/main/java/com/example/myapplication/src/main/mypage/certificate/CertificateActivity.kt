package com.example.myapplication.src.main.mypage.certificate

import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCertificateBinding
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class CertificateActivity :
    BaseActivity<ActivityCertificateBinding>(ActivityCertificateBinding::inflate),
    CertificateActivityView {
    private var body : RequestBody? = null
    private var emptyPart : MultipartBody.Part? = null
//    private val body = route.toString().toRequestBody(MultipartBody.FORM)
//    private val emptyPart = MultipartBody.Part.createFormData("image", "", body)

    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                body = uri.toString().toRequestBody(MultipartBody.FORM)
                emptyPart = MultipartBody.Part.createFormData("image", "image", body!!)

                Glide.with(this)
                    .load(uri)
                    .into(binding.certificateImgShow)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onPostHostSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit2", data.toString())

        showToast("신청되었습니다. 등록까지 약 1주일이 걸릴 수 있습니다.")
    }

    override fun onPostHostFail(message: String) {
        Log.d("Retrofit2_Error", message)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (binding.certificateImgShow.drawable != R.drawable.twitter_ex.toDrawable()) {
            binding.certificateBtnCheck.isClickable = true
        }

        binding.certificateBtnClose.setOnClickListener {
            this.finish()
        }

        binding.certificateBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.certificateBtnCheck.setOnClickListener {
            // 서버로 데이터 보내기
            CertificateService(this).tryPostHost(emptyPart!!)
        }
    }
}