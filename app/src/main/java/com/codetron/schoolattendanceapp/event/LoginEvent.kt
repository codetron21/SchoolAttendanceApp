package com.codetron.schoolattendanceapp.event

sealed class LoginEvent {
    object NavToForgotPassword : LoginEvent()
    object NavToDashboard : LoginEvent()
    object Idle : LoginEvent()
}