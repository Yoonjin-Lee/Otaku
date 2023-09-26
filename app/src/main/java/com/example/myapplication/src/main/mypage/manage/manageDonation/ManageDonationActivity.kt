package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.config.RecyclerViewDecoration
import com.example.myapplication.databinding.ActivityManageDonationBinding

class ManageDonationActivity : BaseActivity<ActivityManageDonationBinding>(ActivityManageDonationBinding::inflate) {
    val itemList = ArrayList<DonationData>()
    val adapter = DonationRVAdapter(itemList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                val p = data!!.getIntExtra("position", -1)
                if (p >= 0){
                    itemList.remove(itemList[p])
                    adapter.notifyItemRemoved(p)
                }else{
                    showToast("포지션 값이 잘못 들어옴")
                }
            }
        }
    }
}


