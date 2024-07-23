package com.elfeky.batteryalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.MutableState

class BatteryLowReceiver(
    private val batteryLowImageState: MutableState<Int>
) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BATTERY_LOW) {
            batteryLowImageState.value = R.drawable.battery_low
        } else if (intent.action == Intent.ACTION_BATTERY_OKAY) {
            batteryLowImageState.value = R.drawable.battery_full
        }
    }

    companion object {
        fun registerReceiver(
            context: Context,
            batteryLowImageState: MutableState<Int>
        ) {
            val intentFilter = IntentFilter().apply {
                addAction(Intent.ACTION_BATTERY_LOW)
                addAction(Intent.ACTION_BATTERY_OKAY)
            }
            val receiver = BatteryLowReceiver(batteryLowImageState)
            context.registerReceiver(receiver, intentFilter)
        }
    }
}