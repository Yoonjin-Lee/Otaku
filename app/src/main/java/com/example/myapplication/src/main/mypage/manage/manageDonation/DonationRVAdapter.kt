package com.example.myapplication.src.main.mypage.manage.manageDonation

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListDonationBinding

class DonationRVAdapter(private val itemList : ArrayList<DonationData>, val context : Context) : RecyclerView.Adapter<DonationRVAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ListDonationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var p : Int? = null
        fun bind(data: DonationData) {
            binding.donationName.text = data.name
            binding.donationMoney.text = data.price
            binding.donationBtnCheck.setOnClickListener {
                val intent = Intent(context, DonationDialog::class.java)
                intent.putExtra("position", p)
                Log.d("adapter_position", "${p}")
                context.startActivity(intent)
            }
        }

        fun setData(position: Int){
            this.p = position
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ListDonationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
        holder.setData(position)
    }

    override fun getItemCount(): Int = itemList.size

    interface OnItemClickListener{
        fun onClick(position : Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    private var itemClickListener: OnItemClickListener? = null

}