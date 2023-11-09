package com.example.myapplication.src.main.home.add.mainPicture

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityMainPictureBinding
import com.example.myapplication.src.main.home.add.addAccount.AddAccountActivity
import com.example.myapplication.src.main.home.add.addInfo.InfoData
import com.example.myapplication.src.main.home.add.mainPicture.model.Request
import com.example.myapplication.src.main.mypage.certificate.UriUtil
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

class MainPictureActivity :
    BaseActivity<ActivityMainPictureBinding>(ActivityMainPictureBinding::inflate),
    MainPictureActivityView {
    private var mainPart: MultipartBody.Part? = null

    // Registers a photo picker activity launcher in single-select mode.
    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        // Callback is invoked after the user selects a media item or closes the
        // photo picker.
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")

            val mainFile = UriUtil.toFile(this, uri)
            val mainBody = mainFile.absolutePath.toRequestBody("multipart/form-data".toMediaType())
            Log.d("MainFile_path", mainFile.absolutePath)
            mainPart = MultipartBody.Part.createFormData("featuredImageFile", mainFile.name, mainBody)

            Glide.with(this)
                .load(uri)
                .into(binding.mainPictureImgShow)
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val infoData = intent.getSerializableExtra("infoData") as InfoData
        Log.d("Retrofit", "$infoData")
        val giftUri = intent.getStringExtra("giftUri")
        val content = intent.getStringExtra("content") as String

        val request = Request(
            infoData.isPublic,
            infoData.xNickname,
            infoData.xId,
            infoData.name,
            infoData.subjectId,
            infoData.openedDate,
            infoData.closedDate,
            infoData.address,
            content
        )

        Log.d("Retrofit", "$request")

        val giftFile = UriUtil.toFile(this, giftUri!!.toUri())
        val giftBody = giftFile.absolutePath.toRequestBody("multipart/form-data".toMediaType())
        Log.d("GiftFile_path", giftFile.absolutePath)
        val giftPart = MultipartBody.Part.createFormData("perksImageFile", giftFile.name, giftBody)

        binding.mainPictureBtnClose.setOnClickListener {
            this.finish()
        }

        binding.mainPictureBtnRegister.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.mainPictureBtnNext.setOnClickListener {
            if (binding.mainPictureImgShow.drawable != null) {
                MainPictureService(this, this).tryPostEvent(
                    mainPart!!,
                    giftPart,
                    request
                )
                Log.d("mainPart", "$mainPart")
                Log.d("giftPart", "$giftPart")
            } else {
                showToast(getString(R.string.fill_picture))
            }
        }
    }

    override fun onPostEventSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        val eventId = data.getInt("eventId")
        val intent = Intent(this@MainPictureActivity, AddAccountActivity::class.java)
        intent.putExtra("eventId", eventId)
        startActivity(intent)
    }

    override fun onPostEventFail(message: String) {
        Log.d("Retrofit", message)
    }
}

object FileUtil {
    // 임시 파일 생성
    fun createTempFile(context: Context, fileName: String): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(storageDir, fileName)
    }

    // 파일 내용 스트림 복사
    fun copyToFile(context: Context, uri: Uri, file: File) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        val buffer = ByteArray(4 * 1024)
        while (true) {
            val byteCount = inputStream!!.read(buffer)
            if (byteCount < 0) break
            outputStream.write(buffer, 0, byteCount)
        }

        outputStream.flush()
    }
}

object UriUtil {
    // URI -> File
    fun toFile(context: Context, uri: Uri): File {
        val fileName = getFileName(context, uri)

        val file = FileUtil.createTempFile(context, fileName)
        FileUtil.copyToFile(context, uri, file)

        return File(file.absolutePath)
    }

    // get file name & extension
    fun getFileName(context: Context, uri: Uri): String {
        val name = uri.toString().split("/").last()
        val ext = context.contentResolver.getType(uri)!!.split("/").last()

        return "$name.$ext"
    }
}