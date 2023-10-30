package com.codetron.schoolattendanceapp.model.message

import android.content.Context
import com.codetron.schoolattendanceapp.R

enum class FieldTypeMessage {
    NISN_EMPTY,
    PASS_EMPTY
}

object InputFieldMessage {

    fun getFieldMessage(context: Context, message: FieldTypeMessage?): String? {
        if (message == FieldTypeMessage.NISN_EMPTY) {
            return context.getString(R.string.nisn_empty)
        }

        if (message == FieldTypeMessage.PASS_EMPTY) {
            return context.getString(R.string.pass_empty)
        }
        return null
    }

}