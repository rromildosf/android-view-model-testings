package com.rrom.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.rrom.viewmodel.model.*
import com.rrom.viewmodel.storage.AddData
import com.rrom.viewmodel.storage.GetData
import com.rrom.viewmodel.storage.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = MainViewModel()

        vm.doAction(AddData("currentCamera", Camera(1, "Camera 1")))
        vm.doAction(GetData(GetCurrentCamera))

        vm.doAction(AddData("device", Device("41687786", "Device 1")))
        vm.doAction(GetData(GetDevice))

        vm.actionResult.observe(this, Observer(::onData) )
    }

    private fun onData(result: ActionResult?) {
        Log.w("MainActivity", "onData received $result")
    }
}
