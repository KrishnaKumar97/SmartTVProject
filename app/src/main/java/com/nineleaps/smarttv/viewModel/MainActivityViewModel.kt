package com.nineleaps.smarttv.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nineleaps.smarttv.model.DeviceDataModel
import com.nineleaps.smarttv.utility.FireBaseCalls
import com.nineleaps.smarttv.utility.ObjectCallback

class MainActivityViewModel : ViewModel() {

    val TAG = "MainActivityViewModel"

    private val fireBaseCalls = FireBaseCalls()

    var dataToDisplay = MutableLiveData<DeviceDataModel>()

    var deviceKey = MutableLiveData<String>()


    /**
     * register device to database
     */
    fun pushDeviceKeyToDataBase() {
        fireBaseCalls.registerDevice(object : ObjectCallback<String> {
            override fun onFailure(e: Exception) {
                Log.e(TAG, e?.message)
            }

            override fun onSuccess(key: String) {
                deviceKey.postValue(key)
            }

        })
        deviceKey.postValue("")
    }

    fun getDataForTheDevice(deviceKey: String) {
        fireBaseCalls.fetchDataForDevice(deviceKey, object : ObjectCallback<DeviceDataModel> {
            override fun onSuccess(`object`: DeviceDataModel) {

            }

            override fun onFailure(e: Exception) {

            }

        })

    }


}