package com.example.jumpstart

import android.os.Bundle
import android.support.wearable.activity.WearableActivity

class AllTasks : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_tasks)

        // Enables Always-on
        setAmbientEnabled()
    }
}