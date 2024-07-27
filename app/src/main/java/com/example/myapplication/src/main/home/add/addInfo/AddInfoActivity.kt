package com.example.myapplication.src.main.home.add.addInfo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddInfoBinding
import com.example.myapplication.src.main.home.add.addInfo.model.CategoryData
import com.example.myapplication.src.main.home.add.addInfo.model.SubjectData
import com.example.myapplication.src.main.home.add.giftPicture.GiftPictureActivity
import org.json.JSONObject
import java.time.LocalDate

class AddInfoActivity : BaseActivity<ActivityAddInfoBinding>(ActivityAddInfoBinding::inflate),
    AddInfoActivityView {
    private val subjectArray = ArrayList<SubjectData>()
    private var subjectId : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AddInfoService(this, this).tryGetSubjects()

        val isPublic = intent.getBooleanExtra("isPublic", false)

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
                return binding.addInfoBtnCategoryIdol.text.toString() //0
            }
            if (actor) {
                return binding.addInfoBtnCategoryActor.text.toString() //1
            }
            if (virtual) {
                return binding.addInfoBtnCategoryVirtual.text.toString() //2
            }
            if (anime) {
                return binding.addInfoBtnCategoryAnime.text.toString() //3
            }
            if (sports) {
                return binding.addInfoBtnCategorySports.text.toString() //4
            }
            if (etc) {
                return binding.addInfoBtnCategoryEtc.text.toString() //5
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

        binding.infoBtnClose.setOnClickListener {
            this.finish()
        }

        binding.infoBtnNext.setOnClickListener {
            val twtName = binding.infoAddEditTwtName.text.toString()
            val twtId = binding.infoAddEditTwtId.text.toString()
            val title = binding.infoAddEditPostTitle.text.toString()
            val main = binding.infoAddEditMain.text.toString()
            val category = resultRadio()
            val year = binding.infoAddEditDateYear.text.toString().toInt()
            val month = binding.infoAddEditDateMonth.text.toString().toInt()
            val day = binding.infoAddEditDateDay.text.toString().toInt()
            val place = binding.infoAddEditPlace.text.toString()

            // 이벤트 대상 찾기
            for(i in subjectArray){
                if (main == i.name){
                    subjectId = i.subjectId
                    break
                }
            }

            // 이벤트 대상이 없으면 추가
            if (subjectId < 0){
                AddInfoService(this, this).tryPostSubject(
                    CategoryData(
                        category,
                        main
                    )
                )
                val infoData = InfoData(
                    isPublic,
                    twtName,
                    twtId,
                    title,
                    subjectId,
                    LocalDate.of(year, month, day),
                    LocalDate.of(year, month, day),
                    place
                )

                Log.d("infoData", infoData.toString())

                if (
                    twtName.isNotEmpty() &&
                    twtId.isNotEmpty() &&
                    title.isNotEmpty() &&
                    main.isNotEmpty() &&
                    category.isNotEmpty() &&
                    year.toString().isNotEmpty() &&
                    month.toString().isNotEmpty() &&
                    day.toString().isNotEmpty() &&
                    place.isNotEmpty()
                ) {
                    val intent = Intent(this, GiftPictureActivity::class.java)
                    intent.putExtra("infoData", infoData)
                    intent.putExtra("subjectId", subjectId)
                    startActivity(intent)
                } else {
                    showToast(getString(R.string.fill_all))
                }
            }else{
                val infoData = InfoData(
                    isPublic,
                    twtName,
                    twtId,
                    title,
                    subjectId,
                    LocalDate.of(year, month, day),
                    LocalDate.of(year, month, day),
                    place
                )

                Log.d("infoData", infoData.toString())

                if (
                    twtName.isNotEmpty() &&
                    twtId.isNotEmpty() &&
                    title.isNotEmpty() &&
                    main.isNotEmpty() &&
                    category.isNotEmpty() &&
                    year.toString().isNotEmpty() &&
                    month.toString().isNotEmpty() &&
                    day.toString().isNotEmpty() &&
                    place.isNotEmpty() &&
                    subjectId > 0
                ) {
                    val intent = Intent(this, GiftPictureActivity::class.java)
                    intent.putExtra("infoData", infoData)
                    intent.putExtra("subjectId", subjectId)
                    startActivity(intent)
                } else {
                    showToast(getString(R.string.fill_all))
                }
            }
        }
    }

    override fun onGetSubjectsSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            subjectArray.add(
                SubjectData(
                    obj.getInt("subjectId"),
                    obj.getString("name")
                )
            )
        }
    }

    override fun onGetSubjectsFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostSubjectSuccess(response: String) {
        subjectId = JSONObject(response).getInt("data")
        Log.d("Retrofit", "subjectId : $subjectId")
    }

    override fun onPostSubjectFail(message: String) {
        Log.d("Retrofit", message)
    }
}