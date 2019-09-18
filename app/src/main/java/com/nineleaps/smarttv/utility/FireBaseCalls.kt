package com.nineleaps.smarttv.utility

import com.google.firebase.database.*
import com.nineleaps.smarttv.model.DeviceDataModel

class FireBaseCalls {

    private val database = FirebaseDatabase.getInstance()
    private var deviceDataListener: ValueEventListener? = null
    private var whatToShowDataFieldListener: ValueEventListener? = null

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

    /**
     * @param callback object to send success or failure from firebase
     * function will fetch the list of userList for the user
     */
    fun fetchDeviceData(
        deviceId: String,
        callback: ObjectCallback<DeviceDataModel?>
    ): DatabaseReference? {

        val myRef = database.getReference("televisions/$deviceId")
        deviceDataListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val result = snapshot.getValue(DeviceDataModel::class.java)
                    callback.onSuccess(result)
                } else
                    callback.onSuccess(null)
            }
        }
        myRef.addValueEventListener(deviceDataListener!!)
        return myRef
    }

    /**
     * Remove the  listner for event back ground image
     */
    fun removeListenerForEventBackgroundImage(customReference: DatabaseReference) {
        if (deviceDataListener != null) {
            customReference.removeEventListener(deviceDataListener!!)
        }
    }
}