package com.nineleaps.smarttv.model

data class DeviceDataModel(

        var defaultImageUrl: String,

        var images: List<String>,

        var isEnabled: Boolean,

        var location: String,

        var name: String,

        var url: String,

        var whatToShow: String
)