package uz.gita.game2048byolmos.mvp.contract

interface HomeContract {
    interface View {
        fun openPlayScreen()
    }

    interface Presenter {
        fun clickPlayButton()
    }
}