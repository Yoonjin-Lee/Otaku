package com.example.myapplication.src.main.mypage.admission.scratch

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityScratchBinding
import com.example.myapplication.src.main.MainActivity
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class ScratchActivity : BaseActivity<ActivityScratchBinding>(ActivityScratchBinding::inflate),
    ScratchActivityView {
    var title = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ScratchService(this).tryGetEventImage(intent.getIntExtra("eventId", 0))

        binding.admissionBtnClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.scratchBtnSave.setOnClickListener {
            val filename = "${title}.jpg"

            val img = binding.scratchImgPresent.drawable.toBitmap()

            //이미지 저장
            saveMediaToStorage(img, filename)
        }

    }

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

    override fun onGetEventImageSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit", "$data")
        // 이미지 받아서 로드
        Glide.with(this)
            .load(data.getString("perksImage").toUri())
            .into(binding.scratchImgPresent)
        title = data.getString("name")
    }

    override fun onGetEventImageFail(message: String) {
        Log.d("Retrofit", message)
    }
}