package com.lynn.foodies

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.lynn.foodies.data.source.local.database.AppDatabase
import com.lynn.foodies.di.AppModules
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(AppModules.modules)
        }

    }
}