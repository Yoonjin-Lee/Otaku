package com.example.myapplication.src.main.home.add.mainPicture

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainPictureBinding
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.home.add.addAccount.AddAccountActivity

class MainPictureActivity : BaseActivity<ActivityMainPictureBinding>(ActivityMainPictureBinding::inflate)  {
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            Glide.with(this)
                .load(uri)
                .into(binding.mainPictureImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainPictureBtnClose.setOnClickListener {
            this.finish()
        }

        binding.mainPictureBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.mainPictureBtnNext.setOnClickListener {
            if (binding.mainPictureImgShow.drawable != null){
                val intent = Intent(this, AddAccountActivity::class.java)
                startActivity(intent)
            } else {
                showToast(getString(R.string.fill_picture))
            }
        }
    }
}