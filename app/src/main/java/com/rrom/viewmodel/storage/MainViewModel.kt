package com.rrom.viewmodel.storage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rrom.viewmodel.model.Action
import com.rrom.viewmodel.model.ActionResult

sealed class ModelAction
data class GetData(var action: Action): ModelAction()
data class AddData(val key: String, val data: Any): ModelAction()

class MainViewModel {

    private val _result: MutableLiveData<ActionResult?> = MutableLiveData()
    val actionResult: LiveData<ActionResult?> = _result

    private val storage = object: DataStorage {
        private val storedObjects = HashMap<String, Any?>()

        override fun <T> addData(key: String, data: T) { storedObjects.put(key, data) }

        override fun <T> getData(key: String) = storedObjects[key] as T?
    }

    private val emitter = object: DataEmitter<ActionResult?> {
        override fun emit(data: ActionResult?) {
            Log.d(TAG, "emitting $data")
            _result.value = data
        }
    }

    fun doAction(modelAction: ModelAction) {
        when(modelAction) {
            is GetData-> modelAction.action.doAction(emitter, storage)
            is AddData -> storage.addData(modelAction.key, modelAction.data)
        }
    }

    companion object {
        val TAG = "MainViewModel"
    }
}