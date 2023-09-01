package com.example.myapplication.src.main.home.addContent

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddContentBinding
import com.example.myapplication.src.main.home.mainPicture.MainPictureActivity

class AddContentActivity : BaseActivity<ActivityAddContentBinding>(ActivityAddContentBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.addContetnBtnClose.setOnClickListener {
            this.finish()
        }

        binding.addContentBtnNext.setOnClickListener {
            if (binding.addContentEditContent.text != null){
                val intent = Intent(this, MainPictureActivity::class.java)
                startActivity(intent)
            } else {
                showToast(getString(R.string.add_content_fill_all))
            }
        }

        val content = binding.addContentEditContent.text.toString()
    }
}