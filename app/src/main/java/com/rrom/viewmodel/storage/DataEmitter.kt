package com.rrom.viewmodel.storage

interface DataEmitter<T> {
    fun emit(data: T?)
}