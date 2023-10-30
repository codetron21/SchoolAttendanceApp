package com.codetron.schoolattendanceapp.helper

import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.update(action: T.() -> T) {
    value = value.action()
}