package com.codetron.schoolattendanceapp.state

import com.codetron.schoolattendanceapp.event.ForgotPasswordEvent
import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage
import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage
import com.codetron.schoolattendanceapp.services.model.Student

data class ForgotPasswordState(
    val nisn: String = "",
    val errorNisn: FieldTypeMessage? = null,
    val loading: Boolean = false,
    val student: Student? = null,
    val serviceMessage: ServiceTypeMessage? = null,
    val event: ForgotPasswordEvent = ForgotPasswordEvent.Idle
)