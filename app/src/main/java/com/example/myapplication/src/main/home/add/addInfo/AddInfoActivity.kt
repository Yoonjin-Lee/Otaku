package com.example.myapplication.src.main.home.add.addInfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddInfoBinding
import com.example.myapplication.src.main.home.add.giftPicture.GiftPictureActivity

class AddInfoActivity : BaseActivity<ActivityAddInfoBinding>(ActivityAddInfoBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var idol = false
        var actor = false
        var virtual = false
        var anime = false
        var sports = false
        var etc = false

        fun initRadioBox() {
            idol = false
            actor = false
            virtual = false
            anime = false
            sports = false
            etc = false

            binding.addInfoBtnCategoryIdol.setBackgroundResource(R.drawable.btn_rectangle)
            binding.addInfoBtnCategoryActor.setBackgroundResource(R.drawable.btn_rectangle)
            binding.addInfoBtnCategoryVirtual.setBackgroundResource(R.drawable.btn_rectangle)
            binding.addInfoBtnCategoryAnime.setBackgroundResource(R.drawable.btn_rectangle)
            binding.addInfoBtnCategorySports.setBackgroundResource(R.drawable.btn_rectangle)
            binding.addInfoBtnCategoryEtc.setBackgroundResource(R.drawable.btn_rectangle)
        }

        fun radioButton(
            iconId: Button,
            checkNum: Boolean
        ): Boolean {
            return if (checkNum) {
                initRadioBox()
                false
            } else {
                initRadioBox()
                iconId.setBackgroundResource(R.drawable.btn_donating_shape)
                true
            }
        }

        fun resultRadio(): String {
            if (idol) {
                return binding.addInfoBtnCategoryIdol.text.toString()
            }
            if (actor) {
                return binding.addInfoBtnCategoryActor.text.toString()
            }
            if (virtual) {
                return binding.addInfoBtnCategoryVirtual.text.toString()
            }
            if (anime) {
                return binding.addInfoBtnCategoryAnime.text.toString()
            }
            if (sports) {
                return binding.addInfoBtnCategorySports.text.toString()
            }
            if (etc) {
                return binding.addInfoBtnCategoryEtc.text.toString()
            }

            return "None"
        }

        binding.addInfoBtnCategoryIdol.setOnClickListener {
            idol = radioButton(binding.addInfoBtnCategoryIdol, idol)
        }

        binding.addInfoBtnCategoryActor.setOnClickListener {
            actor = radioButton(binding.addInfoBtnCategoryActor, actor)
        }

        binding.addInfoBtnCategoryVirtual.setOnClickListener {
            virtual = radioButton(binding.addInfoBtnCategoryVirtual, virtual)
        }

        binding.addInfoBtnCategoryAnime.setOnClickListener {
            anime = radioButton(binding.addInfoBtnCategoryAnime, anime)
        }

        binding.addInfoBtnCategorySports.setOnClickListener {
            sports = radioButton(binding.addInfoBtnCategorySports, sports)
        }

        binding.addInfoBtnCategoryEtc.setOnClickListener {
            etc = radioButton(binding.addInfoBtnCategoryEtc, etc)
        }

        val twtName = binding.infoTxtTwitName.text.toString()
        val twtId = binding.infoAddEditTwtId.text.toString()
        val title = binding.infoTxtPostTitle.text.toString()
        val main = binding.infoAddEditMain.text.toString()
        val category = resultRadio()
        val date = binding.infoAddEditDate.text.toString()
        val place = binding.infoAddEditPlace.text.toString()

        binding.infoBtnClose.setOnClickListener {
            this.finish()
        }

        binding.infoBtnNext.setOnClickListener {
            if (
                twtName.isNotEmpty() &&
                twtId.isNotEmpty() &&
                title.isNotEmpty() &&
                    main.isNotEmpty() &&
                    category.isNotEmpty() &&
                    date.isNotEmpty() &&
                    place.isNotEmpty()){
                val intent = Intent(this, GiftPictureActivity::class.java)
                startActivity(intent)
            } else {
                showToast(getString(R.string.fill_all))
            }
        }

    }
}