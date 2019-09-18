package com.nineleaps.smarttv.utility

interface ObjectCallback<T> {
    fun onSuccess(value: T)
    fun onFailure(e: Exception)
}
