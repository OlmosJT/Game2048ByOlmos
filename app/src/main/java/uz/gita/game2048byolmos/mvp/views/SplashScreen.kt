package uz.gita.game2048byolmos.mvp.views

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.gita.game2048byolmos.R
import uz.gita.game2048byolmos.mvp.contract.HomeContract
import uz.gita.game2048byolmos.mvp.contract.SplashContract
import uz.gita.game2048byolmos.mvp.presenter.SplashPresenter

class SplashScreen: Fragment(R.layout.screen_splash), SplashContract.View  {
    private val presenter: SplashContract.Presenter = SplashPresenter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.openHomeScreen()
    }

    override fun openHomeScreen() {
        findNavController().navigate(R.id.action_splashScreen_to_homeScreen)
    }
}