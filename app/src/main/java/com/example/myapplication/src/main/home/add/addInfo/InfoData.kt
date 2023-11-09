package com.example.myapplication.src.main.home.add.addInfo

import java.time.LocalDate

data class InfoData(
    val isPublic : Boolean,
    val xNickname: String,
    val xId: String,
    val name: String,
    val subjectId: Int,
    val openedDate: LocalDate,
    val closedDate: LocalDate,
    val address: String,
) : java.io.Serializable

