package com.nineleaps.smarttv.activity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nineleaps.smarttv.fragment.WebViewFragment
import com.nineleaps.smarttv.R
import com.nineleaps.smarttv.model.DeviceDataModel
import com.nineleaps.smarttv.viewModel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private var smartTVViewModel: MainActivityViewModel? = null

    lateinit var frameLayout: FrameLayout

    var arrayListOfImageUrl = ArrayList<String>()

    var webUrl = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        frameLayout = findViewById(R.id.fragment_container)
        initViewModel()
        checkForTVSetup()
        observeDataForNewDeviceKey()
        observerForDeviceData()
    }

    /**
     * init view model
     */
    private fun initViewModel() {
        smartTVViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
    }

    /**
     * make call to fire-base to get the data for the device
     */
    private fun makeCallToGetDataForDevice() {
        smartTVViewModel?.getDataForTheDevice()
    }

    /**
     * check for device key is registered with fire-base or not
     * if not register it else make call to get device data
     */
    private fun checkForTVSetup() {
        val deviceKey =
            getSharedPreferences("Preference", Context.MODE_PRIVATE).getString("", "Not-Set")
        if (deviceKey != "Not-Set") {
            // make call to push device key
            makeCallToGetDataForDevice()
        } else {
            makeCallToRegisterDevice()
        }
    }

    /**
     * make call to fire-base to register device
     */
    private fun makeCallToRegisterDevice() {
        smartTVViewModel?.pushDeviceKeyToDataBase()
    }

    /**
     * observe data for new device key
     */
    private fun observeDataForNewDeviceKey() {
        smartTVViewModel?.deviceKey?.observe(this, Observer {
            if (it != null) {
                storeKeyToSharedPreference(it)
            }
            makeCallToGetDataForDevice()
            // Todo store device key to sharedpreference and make call to get data for the device
        })
    }

    private fun observerForDeviceData() {
        smartTVViewModel?.dataToDisplay?.observe(this, Observer {
            if (it == null) {
                makeCallToRegisterDevice()
            } else {
                checkForTheContentType(it)
            }
        })
    }

    /**
     * store device key to shared preference
     */
    @SuppressLint("CommitPrefEdits")
    private fun storeKeyToSharedPreference(deviceKey: String) {
        getSharedPreferences("Preference", Context.MODE_PRIVATE).edit()
            .putString("DEVICE_KEY", deviceKey)
    }

    /**
     * function check for the content type and load fragment according to that
     */
    private fun checkForTheContentType(deviceData: DeviceDataModel) {
        if (deviceData.isEnabled) {
            if (deviceData.whatToShow == "images") {
                // viewpager
                arrayListOfImageUrl.clear()
                arrayListOfImageUrl.addAll(deviceData.images)
                loadFragment(Fragment())
            } else {
                webUrl = deviceData.url
                loadFragment(WebViewFragment())
            }
        } else {
            webUrl = deviceData.defaultImageUrl
            loadFragment(WebViewFragment())
        }
    }

    /**
     * support function for fragment transaction
     */
    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    /**
     * load fragment to fragment container
     */
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(R.id.fragment_container, fragment)
        }
    }


    /**
     * return web url for the web view fragment
     */
    fun getWebUrlForFragment(): String {
        return webUrl
    }

    /**
     * return list of image url for the ViewPager fragment
     */
    fun getListOfImageUrl(): ArrayList<String> {
        return arrayListOfImageUrl
    }
}
