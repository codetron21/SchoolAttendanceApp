package com.codetron.schoolattendanceapp.services

import androidx.compose.animation.core.snap
import com.codetron.schoolattendanceapp.services.config.FirebaseConfig
import com.codetron.schoolattendanceapp.services.message.ServiceTypeMessage
import com.codetron.schoolattendanceapp.services.model.Student
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ForgotPasswordServices {

    private val firesStore = Firebase.firestore
    private val students = FirebaseConfig.studentStore

    fun forgotPassword(
        nisn: String,
        successListener: ((Student?) -> Unit)? = null,
        errListener: ((ServiceTypeMessage) -> Unit)? = null
    ) {
        firesStore
            .collection(students)
            .document(nisn)
            .get()
            .addOnSuccessListener {snapshot->
                val student = snapshot.toObject(Student::class.java)

                if (student == null) {
                    errListener?.invoke(ServiceTypeMessage.ACCOUNT_NOT_REGISTERED)
                    return@addOnSuccessListener
                }

                successListener?.invoke(student)
            }
            .addOnFailureListener { e->
                val ex = e as FirebaseFirestoreException
                if (ex.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                    errListener?.invoke(ServiceTypeMessage.ACCOUNT_NOT_REGISTERED)
                } else {
                    errListener?.invoke(ServiceTypeMessage.UNKNOWN)
                }
            }
    }

}