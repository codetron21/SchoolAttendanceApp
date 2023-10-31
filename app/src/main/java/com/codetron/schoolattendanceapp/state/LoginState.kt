package com.codetron.schoolattendanceapp.state

import com.codetron.schoolattendanceapp.event.LoginEvent
import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage

data class LoginState(
    val nisn: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val errorNisn: FieldTypeMessage? = null,
    val errorPass: FieldTypeMessage? = null,
    val showPassword: Boolean = false,
    val event: LoginEvent = LoginEvent.Idle,
)