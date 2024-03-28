package com.lynn.foodies

import android.app.Application
import com.lynn.foodies.data.source.local.database.AppDatabase

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        AppDatabase.getInstance(this)
    }
}