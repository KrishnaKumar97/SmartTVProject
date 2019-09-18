package com.nineleaps.smarttv.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.nineleaps.smarttv.model.DeviceDataModel
import com.nineleaps.smarttv.utility.FireBaseCalls
import com.nineleaps.smarttv.utility.ObjectCallback

class MainActivityViewModel : ViewModel() {

    val TAG = "MainActivityViewModel"

    private var databaseReference: DatabaseReference? = null

    private val fireBaseCalls = FireBaseCalls()

    var dataToDisplay = MutableLiveData<DeviceDataModel>()

    var deviceKey = MutableLiveData<String>()


    /**
     * register device to database
     */
    fun pushDeviceKeyToDataBase() {
        fireBaseCalls.registerDevice(object : ObjectCallback<String> {
            override fun onFailure(e: Exception) {
                Log.e(TAG, e.message)
            }

            override fun onSuccess(key: String) {
                deviceKey.postValue(key)
            }

        })
    }

    /**
     * @param deviceKey
     * make call to get data for the device
     */
    fun getDataForTheDevice(deviceKey: String) {
        databaseReference = fireBaseCalls.fetchDeviceData(deviceKey, object : ObjectCallback<DeviceDataModel?> {
            override fun onSuccess(data: DeviceDataModel?) {
                dataToDisplay.postValue(data)
            }

            override fun onFailure(e: Exception) {
                Log.e(TAG, e?.message)
            }

        })

    }

    /**
     * remove database listener
     */
    fun removeListener() {
        if(databaseReference != null) {
            fireBaseCalls.removeListener(databaseReference!!)
        }
    }


}