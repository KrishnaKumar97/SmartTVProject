package com.nineleaps.smarttv.utility

interface ObjectCallback<T> {
    fun onSuccess(`object`: T)
    fun onFailure(e: Exception)
}
