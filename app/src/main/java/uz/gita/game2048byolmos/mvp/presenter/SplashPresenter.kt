package uz.gita.game2048byolmos.mvp.presenter

import android.os.Handler
import android.os.Looper
import uz.gita.game2048byolmos.mvp.contract.HomeContract
import uz.gita.game2048byolmos.mvp.contract.SplashContract

class SplashPresenter(private val view: SplashContract.View): SplashContract.Presenter {
    override fun openHomeScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            view.openHomeScreen()
        }, 2000)
    }

}