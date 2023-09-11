package com.example.myapplication.src.main.search.post.donate

import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityDonateBinding

class DonateActivity : BaseActivity<ActivityDonateBinding>(ActivityDonateBinding::inflate) {
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            Glide.with(this)
                .load(uri)
                .into(binding.donateImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.donateBtnClose.setOnClickListener {
            this.finish()
        }

        binding.donateBtnFinish.setOnClickListener {
            if (binding.donateImgShow.drawable != null && binding.donateEditPrice.text.isNotEmpty()){
                this.finish()
            } else {
                showToast("사진 등록 및 금액 입력을 해주세요")
            }
        }

        binding.donateBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    }
}