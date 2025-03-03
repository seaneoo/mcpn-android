package dev.seano.mcpn

import android.app.Application
import dev.seano.mcpn.di.AppModule
import dev.seano.mcpn.di.AppModuleImpl
import timber.log.Timber

class MCPN : Application() {

    companion object {

        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree()) else Timber.plant(Timber.asTree())

        appModule = AppModuleImpl()
    }
}
