package com.example.myapplication.src.main.mypage.manage.manageParticipate

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListParticipateBinding

class ParticipateRVAdapter(private val itemList: ArrayList<ParticipateData>, val context: Context) :
    RecyclerView.Adapter<ParticipateRVAdapter.ViewHolder>(),
    ManageParticipateActivityView {
    inner class ViewHolder(private val binding: ListParticipateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val button = binding.participateBtnCheck
        fun bind(data: ParticipateData) {
            binding.participateName.text = data.name
            binding.participateId.text = data.id
            binding.participateBtnCheck.setOnClickListener {
                ManageParticipateService(this@ParticipateRVAdapter).tryPostUser(data.approvalId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding =
            ListParticipateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.button.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener {
        fun onClick(v : View, position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener

    override fun onGetUsersSuccess(response: String){}
    override fun onGetUsersFail(message: String) {}
    override fun onPostUserSuccess(response: String) {
        Toast.makeText(context, "승인되었습니다", Toast.LENGTH_LONG).show()
    }

    override fun onPostUserFail(message: String) {
        Log.d("Retrofit", message)
    }
}