package com.codetron.schoolattendanceapp.state

import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage

data class LoginState(
    val nisn: String = "",
    val password: String = "",
    val errorNisn: FieldTypeMessage? = null,
    val errorPass: FieldTypeMessage? = null,
    val showPassword: Boolean = false,
    val message:String? = null,
)