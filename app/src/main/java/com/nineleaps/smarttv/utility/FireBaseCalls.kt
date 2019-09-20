package com.nineleaps.smarttv.utility

import com.google.firebase.database.*
import com.nineleaps.smarttv.model.DeviceDataModel

class FireBaseCalls {

    private val database = FirebaseDatabase.getInstance()
    private var deviceDataListener: ValueEventListener? = null


    /**
     * register device to the fire-base
     */
    fun registerDevice(callback: ObjectCallback<String>) {

        val myRef = database.getReference("televisions")
        val key = myRef.push().key

        if (key != null) {
            myRef.child(key).child("defaultImageUrl")
                .setValue("https://firebasestorage.googleapis.com/v0/b/smarttv-7ea65.appspot.com/o/defaultImages%2FAbstract-Colors-4K-Wallpaper-3840x2160.jpg?alt=media&token=bc626d45-ae27-4917-8193-bb98bc5c6352")
            myRef.child(key).child("images").setValue(ArrayList<String>())
            myRef.child(key).child("isEnabled").setValue(false)
            myRef.child(key).child("location").setValue("")
            myRef.child(key).child("name").setValue("")
            myRef.child(key).child("url").setValue("https://www.nineleaps.com/company/")
            myRef.child(key).child("whatToShow").setValue("webPage")
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
                    result?.isEnabled = snapshot.child("isEnabled").value as Boolean?
                    callback.onSuccess(result)
                } else
                    callback.onSuccess(null)
            }
        }
        myRef.addValueEventListener(deviceDataListener!!)
        return myRef
    }


    /**
     * Remove the  listener
     */
    fun removeListener(customReference: DatabaseReference) {
        if (deviceDataListener != null) {
            customReference.removeEventListener(deviceDataListener!!)
        }
    }
}
