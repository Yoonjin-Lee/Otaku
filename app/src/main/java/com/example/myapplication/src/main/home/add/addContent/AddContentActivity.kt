package com.example.myapplication.src.main.home.add.addContent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddContentBinding
import com.example.myapplication.src.main.home.add.addInfo.InfoData
import com.example.myapplication.src.main.home.add.mainPicture.MainPictureActivity

class AddContentActivity : BaseActivity<ActivityAddContentBinding>(ActivityAddContentBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val infoData = intent.getSerializableExtra("infoData") as InfoData
        Log.d("Retrofit", "$infoData")
        val giftUri = intent.getStringExtra("giftUri")

        binding.addContetnBtnClose.setOnClickListener {
            this.finish()
        }

        binding.addContentBtnNext.setOnClickListener {
            if (binding.addContentEditContent.text != null){
                val intent = Intent(this@AddContentActivity, MainPictureActivity::class.java)
                intent.putExtra("infoData", infoData)
                intent.putExtra("giftUri", giftUri)
                intent.putExtra("content", binding.addContentEditContent.text.toString())
                startActivity(intent)
            } else {
                showToast(getString(R.string.add_content_fill_all))
            }
        }
    }
}