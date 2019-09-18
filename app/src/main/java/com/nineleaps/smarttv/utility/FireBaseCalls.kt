package com.nineleaps.smarttv.utility

import com.google.firebase.database.FirebaseDatabase

class FireBaseCalls {

    private val database = FirebaseDatabase.getInstance()

    /**
     * register device to the fire-base
     */
    fun registerDevice(callback: ObjectCallback<String>) {

        val myRef = database.getReference("televisions")
        val key = myRef.push().key

        if (key != null) {
            myRef.child(key).child("defaultImageUrl").setValue("https://www.nineleaps.com")
            myRef.child(key).child("images").setValue("")
            myRef.child(key).child("isEnabled").setValue(false)
            myRef.child(key).child("location").setValue("")
            myRef.child(key).child("name").setValue("")
            myRef.child(key).child("url").setValue("")
            myRef.child(key).child("whatToShow").setValue("")
            callback.onSuccess(key)
        }

    }
}