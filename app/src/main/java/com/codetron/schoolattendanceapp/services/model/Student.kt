package com.codetron.schoolattendanceapp.services.model

import android.os.Parcelable
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    @get:PropertyName("nisn")
    @set:PropertyName("nisn")
    var nisn: String? = null,
    @get:PropertyName("password")
    @set:PropertyName("password")
    var password: String? = null,
    @get:PropertyName("name")
    @set:PropertyName("name")
    var name: String? = null,
    @get:PropertyName("birth_date")
    @set:PropertyName("birth_date")
    var birthDate: String? = null,
    @get:PropertyName("classroom")
    @set:PropertyName("classroom")
    var classroom: String? = null,
    @get:PropertyName("grade")
    @set:PropertyName("grade")
    var grade: Int? = null,
) : Parcelable