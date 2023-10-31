package com.codetron.schoolattendanceapp.event

import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage

sealed class ForgotPasswordEvent {
    class ShowServicesMessage(val message: ServiceTypeMessage) : ForgotPasswordEvent()
    object NavBack : ForgotPasswordEvent()
    object Idle : ForgotPasswordEvent()
}