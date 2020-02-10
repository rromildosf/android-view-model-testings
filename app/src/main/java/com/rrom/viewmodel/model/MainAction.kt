package com.rrom.viewmodel.model

import com.rrom.viewmodel.storage.DataEmitter
import com.rrom.viewmodel.storage.DataStorage

sealed class Action {
    abstract fun doAction(emitter: DataEmitter<ActionResult?>, storage: DataStorage)
}
object GetUser: Action() {
    override  fun doAction(emitter: DataEmitter<ActionResult?>, storage: DataStorage) {
        emitter.emit(UserInfo(storage.getData<User>("user")))
    }
}

object GetDevice: Action() {
    override fun doAction(emitter: DataEmitter<ActionResult?>, storage: DataStorage) {
        emitter.emit(DeviceInfo(storage.getData<Device>("device")))
    }
}

object GetCurrentCamera: Action() {
    override fun doAction(emitter: DataEmitter<ActionResult?>, storage: DataStorage) {
        emitter.emit(CurrentCameraInfo(storage.getData<Camera>("currentCamera")))
    }
}

data class GetCamera(val id: Int): Action() {
    override fun doAction(emitter: DataEmitter<ActionResult?>, storage: DataStorage) {
        val cameras = storage.getData<List<Camera>>("cameras")
        if (cameras != null && cameras.size > 0)
            emitter.emit(CameraInfo(id, cameras.find { it.number == id }))
        else emitter.emit(CameraInfo(id, null))
    }
}


sealed class ActionResult
data class CameraInfo(val id: Int, val data: Any?): ActionResult()
data class CurrentCameraInfo(val data: Any?): ActionResult()
data class DeviceInfo(val data: Any?): ActionResult()
data class UserInfo(val data: Any?): ActionResult()
