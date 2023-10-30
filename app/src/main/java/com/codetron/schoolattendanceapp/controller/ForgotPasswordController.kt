package com.codetron.schoolattendanceapp.controller

import com.codetron.schoolattendanceapp.helper.update
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator
import com.codetron.schoolattendanceapp.state.ForgotPasswordState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ForgotPasswordController(
    private val fieldValidator: InputFieldValidator
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

    fun onSubmitClicked() {

    }

}