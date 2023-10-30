package com.codetron.schoolattendanceapp.model.validator

import com.codetron.schoolattendanceapp.model.message.FieldTypeMessage

class InputFieldValidator {
    fun nisnValidation(nisn: String): FieldTypeMessage? {
        if (nisn.isEmpty()) return FieldTypeMessage.NISN_EMPTY
        return null
    }

    fun passwordValidation(password: String): FieldTypeMessage? {
        if (password.isEmpty()) return FieldTypeMessage.PASS_EMPTY
        return null
    }

}