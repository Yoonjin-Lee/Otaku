package com.example.myapplication.src.main.mypage.manage.manageParticipate

interface ManageParticipateActivityView {
    fun onGetUsersSuccess(response: String)
    fun onGetUsersFail(message: String)
    fun onPostUserSuccess(response: String)
    fun onPostUserFail(message: String)
}