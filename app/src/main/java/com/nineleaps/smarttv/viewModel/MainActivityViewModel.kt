package com.nineleaps.smarttv.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nineleaps.smarttv.model.DeviceDataModel

class MainActivityViewModel : ViewModel() {

    var dataToDisplay = MutableLiveData<DeviceDataModel>()

    var deviceKey = MutableLiveData<String>()


    fun pushDeviceKeyToDataBase() {
        deviceKey.postValue("")
    }

    fun getDataForTheDevice() {
        //dataToDisplay.postValue()
    }


}