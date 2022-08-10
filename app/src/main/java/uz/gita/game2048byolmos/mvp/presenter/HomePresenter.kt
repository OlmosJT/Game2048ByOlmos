package uz.gita.game2048byolmos.mvp.presenter

import uz.gita.game2048byolmos.mvp.contract.HomeContract

class HomePresenter(private val view: HomeContract.View): HomeContract.Presenter {
    override fun clickPlayButton() {
        view.openPlayScreen()
    }
}