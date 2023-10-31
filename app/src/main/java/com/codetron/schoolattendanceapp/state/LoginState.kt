package com.codetron.schoolattendanceapp.state

import com.codetron.schoolattendanceapp.event.LoginEvent
import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage
import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage

data class LoginState(
    val nisn: String = "",
    val password: String = "",
    val loading: Boolean = false,
    val errorNisn: FieldTypeMessage? = null,
    val errorPass: FieldTypeMessage? = null,
    val serviceMessage: ServiceTypeMessage? = null,
    val showPassword: Boolean = false,
    val event: LoginEvent = LoginEvent.Idle,
)