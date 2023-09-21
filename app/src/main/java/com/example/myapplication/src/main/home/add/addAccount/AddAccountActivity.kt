package com.example.myapplication.src.main.home.add.addAccount

import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddAccountBinding
import com.example.myapplication.src.main.home.add.addContent.AddContentActivity

class AddAccountActivity :
    BaseActivity<ActivityAddAccountBinding>(ActivityAddAccountBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val money = binding.addAccountEditMoney.text.toString()
        val name = binding.addAccountEditName.text.toString()
        val account = binding.addAccountEditAccount.text.toString()

        binding.addAccountBtnClose.setOnClickListener {
            this.finish()
        }

        binding.addAccountBtnNext.setOnClickListener {
            if (binding.addAccountEditMoney.text != null) {
                if (money != "0") {
                    if (binding.addAccountEditName.text != null && binding.addAccountEditAccount.text != null) {
                        val intent = Intent(this, AddContentActivity::class.java)
                        startActivity(intent)
                    } else{
                        showToast(getString(R.string.fill_all))
                    }
                } else {
                    val intent = Intent(this, AddContentActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}