package com.codetron.schoolattendanceapp.services.message

import android.content.Context
import com.codetron.schoolattendanceapp.R

enum class ServiceTypeMessage {
    ACCOUNT_NOT_REGISTERED,
    PASSWORD_INCORRECT,
    UNKNOWN
}

object MessageServices {
    fun getMessage(context: Context, message: ServiceTypeMessage): String? {
        if (message == ServiceTypeMessage.ACCOUNT_NOT_REGISTERED) return context.getString(R.string.err_account_not_registered)
        if (message == ServiceTypeMessage.PASSWORD_INCORRECT) return context.getString(R.string.err_password_incorrect)
        if (message == ServiceTypeMessage.UNKNOWN) return context.getString(R.string.err_general)
        return null
    }

}