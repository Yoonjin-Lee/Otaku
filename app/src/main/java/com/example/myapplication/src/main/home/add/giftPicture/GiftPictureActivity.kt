package com.example.myapplication.src.main.home.add.giftPicture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityGiftPictureBinding
import com.example.myapplication.src.main.home.add.addContent.AddContentActivity
import com.example.myapplication.src.main.home.add.addInfo.InfoData

class GiftPictureActivity : BaseActivity<ActivityGiftPictureBinding>(ActivityGiftPictureBinding::inflate) {
    private var giftUri : Uri? = null
    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            giftUri = uri

            Glide.with(this)
                .load(uri)
                .into(binding.giftPictureImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val infoData = intent.getSerializableExtra("infoData") as InfoData
        Log.d("Retrofit", "$infoData")

        if (infoData.subjectId < 0){
            infoData.subjectId = intent.getIntExtra("subjectId", 0)
        }

        binding.giftPictureBtnClose.setOnClickListener {
            this.finish()
        }

        binding.giftPictureBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.giftPictureBtnNext.setOnClickListener {
            if (binding.giftPictureImgShow.drawable != null){
                val intent = Intent(this, AddContentActivity::class.java)
                intent.putExtra("infoData", infoData)
                intent.putExtra("giftUri", giftUri.toString())
                startActivity(intent)
            } else {
                showToast(getString(R.string.fill_picture))
            }
        }
    }
}