package com.example.myapplication.src.main.mypage.present

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityPresentBinding
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class PresentActivity : BaseActivity<ActivityPresentBinding>(ActivityPresentBinding::inflate),
    PresentActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventId = intent.getIntExtra("eventId", 0)
        PresentService(this).tryGetImage(eventId)

        binding.presentBtnClose.setOnClickListener {
            this.finish()
        }

        binding.presentBtnSave.setOnClickListener {

            val filename = "${binding.presentTxtEvent.text}.jpg"

            val img = binding.presentImgShow.drawable.toBitmap()

            //이미지 저장
            saveMediaToStorage(img, filename)
        }
    }

    //이미지를 저장하는 함수
    private fun saveMediaToStorage(bitmap: Bitmap, filename: String) {

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver?.also { resolver ->

                // Content resolver will process the contentvalues
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an outputstream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            showToast("사진을 저장하였습니다")
        }
    }

    override fun onGetImageSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        binding.presentTxtEvent.text = data.getString("name")
        Glide.with(this)
            .load(data.getString("perksImage"))
            .into(binding.presentImgShow)
    }

    override fun onGetImageFail(message: String) {
        Log.d("Retrofit", message)
    }
}