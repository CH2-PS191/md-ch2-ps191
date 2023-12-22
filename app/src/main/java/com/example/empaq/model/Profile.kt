package com.example.empaq.model

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object Profile {
    private var user = Firebase.auth.currentUser

    var name = user?.displayName
    val picture = user?.photoUrl ?: "https://fisika.uad.ac.id/wp-content/uploads/blank-profile-picture-973460_1280.png"
    var gender = "Laki-laki"
    var dateOfBirth = "2002-08-17"
    var email = user?.email
}