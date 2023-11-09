package com.example.myapplication.src.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Bundle
import android.util.Log
import com.example.myapplication.config.ApplicationClass
import com.example.myapplication.config.BaseActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.src.login.models.PostSignUpRequest
import com.example.myapplication.src.main.MainActivity
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import org.json.JSONObject


class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate),
    LoginActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val keyHash = Utility.getKeyHash(this)
        Log.d("KeyHash : ", keyHash)

        binding.loginBtnKakao.setOnClickListener {
            startKaKaoLogin()
        }
    }

    override fun onPostSignUpSuccess(response: String) {
        val data = JSONObject(response).getJSONObject("data")
        Log.d("Retrofit2", data.toString())

        val accessToken = data.getString("accessToken")
        val refreshToken = data.getString("refreshToken")
        val editer = ApplicationClass.sSharedPreferences.edit()
        var role = false
        if (data.getString("role") != "ROLE_USER"){
            role = true
        }
        editer.putBoolean("role", role).apply()
        editer.putString("accessToken", accessToken).apply()
        editer.putString("refreshToken", refreshToken).apply()

        Log.d(
            "shared_accessToken",
            ApplicationClass.sSharedPreferences.getString("accessToken", "") ?: "없음"
        )

        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun onPostSignUpFail(message: String) {
        Log.d("Retrofit2_Error", message)
    }


    private fun startKaKaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        Log.d("kakaoLogin", "함수 성공")

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")

                UserApiClient.instance.me { user, error ->
                    if (error != null) {
                        Log.e(TAG, "사용자 정보 요청 실패", error)
                    } else if (user != null) {
                        Log.i(
                            TAG, "사용자 정보 요청 성공" +
                                    "\n이메일: ${user.kakaoAccount?.email}"
                        )

                        // 닉네임, 이메일 가져오기
                        val userNickname = user.kakaoAccount?.profile?.nickname
                        val userEmail = user.kakaoAccount?.email

                        val editer = ApplicationClass.sSharedPreferences.edit()
                        editer.putString("user", userNickname).apply()

                        //통신
                        LoginService(this).tryPostSignUp(
                            PostSignUpRequest(
                                userNickname ?: "",
                                userEmail ?: ""
                            )
                        )
                    }
                }
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            Log.e(TAG, "사용자 정보 요청 실패", error)
                        } else if (user != null) {
                            Log.i(
                                TAG, "사용자 정보 요청 성공" +
                                        "\n이메일: ${user.kakaoAccount?.email}"
                            )

                            // 이메일, 닉네임 가져오기
                            val userNickname = user.kakaoAccount?.profile?.nickname
                            val userEmail = user.kakaoAccount?.email

                            // 통신
                            LoginService(this).tryPostSignUp(
                                PostSignUpRequest(
                                    userNickname ?: "",
                                    userEmail ?: ""
                                )
                            )
                        }
                    }
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
}