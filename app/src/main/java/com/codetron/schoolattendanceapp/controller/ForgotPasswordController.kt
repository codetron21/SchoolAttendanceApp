package com.codetron.schoolattendanceapp.controller

import com.codetron.schoolattendanceapp.event.ForgotPasswordEvent
import com.codetron.schoolattendanceapp.helper.update
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.services.ForgotPasswordServices
import com.codetron.schoolattendanceapp.state.ForgotPasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordController(
    private val fieldValidator: InputFieldValidator,
    private val services: ForgotPasswordServices,
) {
    private val _state = MutableStateFlow(ForgotPasswordState())
    val state = _state.asStateFlow()

    fun onNisnChanged(value: String) {
        _state.update {
            copy(
                nisn = value,
                errorNisn = fieldValidator.nisnValidation(value)
            )
        }
    }

    fun onBackClicked() {
        _state.update { copy(event = ForgotPasswordEvent.NavBack) }
    }

    fun cleanEvent() {
        _state.update { copy(event = ForgotPasswordEvent.Idle) }
    }

    fun clearMessage() {
        _state.update { copy(serviceMessage = null) }
    }

    fun clearStudent() {
        _state.update { copy(student = null) }
    }

    fun onSubmitClicked() {
        val nisn = _state.value.nisn

        onNisnChanged(nisn)

        val errNisn = _state.value.errorNisn

        if (errNisn != null) return

        _state.update { copy(loading = true) }

        services.forgotPassword(nisn,
            successListener = { st ->
                _state.update {
                    copy(
                        loading = false,
                        student = st,
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