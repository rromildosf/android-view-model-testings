package com.rrom.viewmodel.storage

interface DataStorage {
    fun <T> addData(key: String, data: T)
    fun <T> getData(key: String): T?
}