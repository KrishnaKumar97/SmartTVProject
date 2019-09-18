package com.nineleaps.smarttv.model

data class DeviceDataModel(

        var defaultImageUrl: String? = null,

        var images: List<String>? = null,

        var isEnabled: Boolean? = false,

        var location: String? = null,

        var name: String? = null,

        var url: String? = null,

        var whatToShow: String? = null
)