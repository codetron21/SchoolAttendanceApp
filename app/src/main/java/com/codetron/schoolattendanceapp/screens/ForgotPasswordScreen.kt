package com.codetron.schoolattendanceapp.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.codetron.schoolattendanceapp.controller.ForgotPasswordController
import com.codetron.schoolattendanceapp.model.validator.InputFieldValidator

@Composable
fun ForgotPasswordScreen(
    controller:ForgotPasswordController = remember {
        ForgotPasswordController(fieldValidator = InputFieldValidator())
    }
) {

    val focusManager = LocalFocusManager.current

}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {

}