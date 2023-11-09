package com.example.myapplication.src.main.home.add.addAccount

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityAddAccountBinding
import com.example.myapplication.src.main.MainActivity
import com.example.myapplication.src.main.home.add.addAccount.model.AccountData
import com.example.myapplication.src.main.home.add.addContent.AddContentActivity
import org.json.JSONObject

class AddAccountActivity :
    BaseActivity<ActivityAddAccountBinding>(ActivityAddAccountBinding::inflate),
    AddAccountActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val eventId = intent.getIntExtra("eventId", 0)

        binding.addAccountBtnClose.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.addAccountBtnNext.setOnClickListener {
            Log.d("Retrofit", "${binding.addAccountEditMoney.text.isNotEmpty()}")
            if (binding.addAccountEditMoney.text.isNotEmpty()) {
                val money = binding.addAccountEditMoney.text.toString()
                val name = binding.addAccountEditName.text.toString()
                val account = binding.addAccountEditAccount.text.toString()
                val bank = binding.addAccountEditBank.text.toString()
                if (money.toInt() != 0) {
                    if (binding.addAccountEditName.text != null &&
                        binding.addAccountEditAccount.text != null &&
                        binding.addAccountEditBank.text != null
                    ) {
                        AddAccountService(this, this).tryPostSupport(
                            eventId,
                            AccountData(
                                bank,
                                account,
                                name,
                                money.toInt()
                            )
                        )
                    } else {
                        showToast(getString(R.string.fill_all))
                    }
                }
            }else {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }

    override fun onPostSupportSuccess(response: String) {
        val data = JSONObject(response)
        Log.d("Retrofit", "$data")
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onPostSupportFail(message: String) {
        Log.d("Retrofit", message)
    }
}