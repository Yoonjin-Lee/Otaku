package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityManageDonationBinding
import org.json.JSONObject

class ManageDonationActivity :
    BaseActivity<ActivityManageDonationBinding>(ActivityManageDonationBinding::inflate),
    ManageDonationActivityView {
    private var supportId : Int = 0
    private val itemList = ArrayList<DonationData>()
    val adapter = DonationRVAdapter(itemList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportId = intent.getIntExtra("supportId", 0)
        ManageDonationService(this).tryGetSupports(supportId)

        binding.manageDonationBtnClose.setOnClickListener {
            this.finish()
        }

        itemList.apply {
            add(DonationData("이윤진", "10000"))
        }

        binding.manageDonationRv.adapter = adapter
        binding.manageDonationRv.layoutManager = LinearLayoutManager(this)
        binding.manageDonationRv.addItemDecoration(RecyclerViewDecoration(8))

//        DonationDialog().setItemClickListener(object : DonationDialog.OnItemClickListener{
//            override fun onClick(position: Int) {
//                itemList.remove(itemList[position])
//                adapter.notifyItemRemoved(position)
//            }
//        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // B액티비티에서 Yes 버튼을 누른 경우
                ManageDonationService(this).tryPostSupport(supportId)
                val p = data!!.getIntExtra("position", -1)
                if (p >= 0) {
                    itemList.remove(itemList[p])
                    adapter.notifyItemRemoved(p)
                } else {
                    showToast("포지션 값이 잘못 들어옴")
                }
            }
        }
    }

    override fun onGetSupportsSuccess(response: String) {
        val data = JSONObject(response).getJSONArray("data")
        for (i in 0 until data.length()){
            val obj = data.getJSONObject(i)
            itemList.add(
                DonationData(
                    obj.getString("accountHolder"),
                    obj.getString("supportAmount")
                )
            )
        }
        adapter.notifyDataSetChanged()
    }

    override fun onGetSupportsFail(message: String) {
        Log.d("Retrofit", message)
    }

    override fun onPostSupportSuccess(response: String) {
        val data = JSONObject(response).getString("data")
        Log.d("Retrofit", "$data")
        showToast("후원을 성공적으로 확인했습니다.")
    }

    override fun onPostSupportFail(message: String) {
        Log.d("Retrofit", message)
    }
}


