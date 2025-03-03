package dev.seano.mcpn

import android.app.Application
import dev.seano.mcpn.di.AppModule
import dev.seano.mcpn.di.AppModuleImpl

class MCPN : Application() {

    companion object {

        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }
}
