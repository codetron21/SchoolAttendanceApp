package com.codetron.schoolattendanceapp.state

import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage

data class ForgotPasswordState(
    val nisn: String = "",
    val errorNisn: FieldTypeMessage? = null,
)