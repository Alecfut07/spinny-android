package com.alexiscrack3.spinny

import com.alexiscrack3.spinny.login.loginModule
import org.koin.core.context.loadKoinModules

object SpinnyModule {

    fun init() {
        NetworkModule.init()

        val moduleList = listOf(
            loginModule
        )
        loadKoinModules(moduleList)
    }
}
