package com.codetron.schoolattendanceapp.event

import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage

sealed class LoginEvent {
    class ShowServicesMessage(val message: ServiceTypeMessage) : LoginEvent()
    object NavToForgotPassword : LoginEvent()
    object NavToDashboard : LoginEvent()
    object Idle : LoginEvent()
}