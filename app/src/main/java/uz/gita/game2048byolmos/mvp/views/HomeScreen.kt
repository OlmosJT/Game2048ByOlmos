package uz.gita.game2048byolmos.mvp.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.game2048byolmos.R
import uz.gita.game2048byolmos.databinding.ScreenHomeBinding
import uz.gita.game2048byolmos.mvp.contract.HomeContract
import uz.gita.game2048byolmos.mvp.contract.SplashContract
import uz.gita.game2048byolmos.mvp.presenter.HomePresenter
import uz.gita.game2048byolmos.mvp.presenter.SplashPresenter

class HomeScreen: Fragment(R.layout.screen_home), HomeContract.View {
//    private val presenter: SplashContract.Presenter = SplashPresenter(this)
    private val presenter: HomeContract.Presenter = HomePresenter(this)
    private val binding by viewBinding(ScreenHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnPlay.setOnClickListener {
            presenter.clickPlayButton()
        }
    }

    override fun openPlayScreen() {
        findNavController().navigate(R.id.action_homeScreen_to_gameScreen)
    }


}