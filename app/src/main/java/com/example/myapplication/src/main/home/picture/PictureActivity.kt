package com.example.myapplication.src.main.home.picture

import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityPictureBinding

class PictureActivity : BaseActivity<ActivityPictureBinding>(ActivityPictureBinding::inflate) {
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            Glide.with(this)
                .load(uri)
                .into(binding.pictureImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.pictureBtnClose.setOnClickListener {
            this.finish()
        }

        binding.pictureBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.pictureBtnNext.setOnClickListener {

        }
    }
}