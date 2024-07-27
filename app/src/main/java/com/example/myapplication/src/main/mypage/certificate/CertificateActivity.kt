package com.example.myapplication.src.main.mypage.certificate

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toDrawable
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityCertificateBinding
import com.example.myapplication.src.main.home.add.mainPicture.FileUtil
import com.example.myapplication.src.main.home.add.mainPicture.FormDataUtil
import com.example.myapplication.src.main.home.add.mainPicture.UriUtil
import okhttp3.MultipartBody
import org.json.JSONObject
import java.io.File


class CertificateActivity :
    BaseActivity<ActivityCertificateBinding>(ActivityCertificateBinding::inflate),
    CertificateActivityView {
    private var emptyPart: MultipartBody.Part? = null

    // Registers a photo picker activity launcher in single-select mode.
    private val pickMedia =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: ${uri.path}")
                Log.d("PhotoPicker", "Selected URI: $uri")

                val path = UriUtil.toFile(this, uri)
                emptyPart = FormDataUtil.getImageMultipart("image", path)

                Glide.with(this)
                    .load(uri)
                    .into(binding.certificateImgShow)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onPostHostSuccess(response: String) {
        val status = JSONObject(response).getInt("status")
        if (status == 200){
            val data = JSONObject(response).getString("data")
            Log.d("Retrofit2", data)

            showToast("신청되었습니다. 등록까지 약 1주일이 걸릴 수 있습니다.")
            binding.certificateBtnCheck.isEnabled = false
        } else{
            showToast(JSONObject(response).getString("message"))
        }
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
            if (binding.certificateImgShow.drawable != null){
                CertificateService(this, this).tryPostHost(emptyPart!!)
            }else{
                showToast("사진을 등록해주세요")
            }
        }
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