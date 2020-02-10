package com.rrom.viewmodel.data

import com.rrom.viewmodel.model.Device

class CamerasRepository {
    fun getDevice() = Device(
        "" + (Math.random() * 1000000000).toInt().toString(),
        "Device " + (Math.random() * 10).toInt())
}