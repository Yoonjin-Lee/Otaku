package com.example.myapplication.src.main.mypage.certificate

import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCertificateBinding

class CertificateActivity : BaseActivity<ActivityCertificateBinding>(ActivityCertificateBinding::inflate) {
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            Glide.with(this)
                .load(uri)
                .into(binding.certificateImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.certificateBtnClose.setOnClickListener {
            this.finish()
        }

        binding.certificateBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.certificateBtnCheck.setOnClickListener {
            // 서버로 데이터 보내기
        }
    }
}