package com.codetron.schoolattendanceapp.controller

import com.codetron.schoolattendanceapp.event.LoginEvent
import com.codetron.schoolattendanceapp.helper.update
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.services.LoginServices
import com.codetron.schoolattendanceapp.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginController(
    private val fieldValidator: InputFieldValidator,
    private val services: LoginServices,
) {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun onPasswordToggle() {
        _state.update { copy(showPassword = showPassword.not()) }
    }

    fun onNisnChanged(value: String) {
        _state.update {
            copy(
                nisn = value,
                errorNisn = fieldValidator.nisnValidation(value)
            )
        }
    }

    fun onPasswordChanged(value: String) {
        _state.update {
            copy(
                password = value,
                errorPass = fieldValidator.passwordValidation(value)
            )
        }
    }

    fun onForgotPasswordClicked() {
        _state.update { copy(event = LoginEvent.NavToForgotPassword) }
    }

    fun cleanEvent() {
        _state.update { copy(event = LoginEvent.Idle) }
    }

    fun clearMessage() {
        _state.update { copy(serviceMessage = null) }
    }

    fun onLoginClicked() {
        val nisn = _state.value.nisn
        val password = _state.value.password

        onNisnChanged(nisn)
        onPasswordChanged(password)

        val errNisn = _state.value.errorNisn
        val errPass = _state.value.errorPass

        if (errNisn != null || errPass != null) return

        _state.update { copy(loading = true) }

        services.login(
            nisn = nisn,
            password = password,
            successListener = {
                _state.update {
                    copy(
                        event = LoginEvent.NavToDashboard,
                        loading = false
                    )
                }
            },
            errListener = { message ->
                _state.update {
                    copy(
                        serviceMessage = message,
                        loading = false
                    )
                }
            }
        )
    }

}