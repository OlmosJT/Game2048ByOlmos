package uz.gita.game2048byolmos.app

import android.app.Application
import uz.gita.game2048byolmos.data.local.SharedPref
import uz.gita.game2048byolmos.data.repository.impl.AppRepositoryImpl

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        AppRepositoryImpl.init(this)
        SharedPref.init(this)
    }
}