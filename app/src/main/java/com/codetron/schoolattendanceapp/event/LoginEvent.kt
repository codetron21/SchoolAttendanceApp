package com.codetron.schoolattendanceapp.event

import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage

sealed class LoginEvent {
    object NavToForgotPassword : LoginEvent()
    object NavToDashboard : LoginEvent()
    object Idle : LoginEvent()
}