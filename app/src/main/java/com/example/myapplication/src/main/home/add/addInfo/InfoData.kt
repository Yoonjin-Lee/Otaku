package com.example.myapplication.src.main.home.add.addInfo

import java.time.LocalDate

data class InfoData(
    val isPublic : Boolean,
    val xNickname: String,
    val xId: String,
    val name: String,
    var subjectId: Int,
    val openedDate: LocalDate,
    val closedDate: LocalDate,
    val address: String,
) : java.io.Serializable

