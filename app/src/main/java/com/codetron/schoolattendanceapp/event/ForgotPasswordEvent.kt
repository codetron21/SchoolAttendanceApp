package com.codetron.schoolattendanceapp.event

sealed class ForgotPasswordEvent {
    object NavBack : ForgotPasswordEvent()
    object Idle : ForgotPasswordEvent()
}