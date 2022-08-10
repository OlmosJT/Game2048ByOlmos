package uz.gita.game2048byolmos.mvp.presenter

import uz.gita.game2048byolmos.mvp.contract.GameContract
import uz.gita.game2048byolmos.mvp.model.GameModel

class GamePresenter(private val view: GameContract.View): GameContract.Presenter {
    // private val model : MainContract.Model = MainModel()
    private val model: GameContract.Model = GameModel()

    override fun startPlay() {
        view.illustrateMatrix(model.getMatrixData())
    }

    override fun saveData() {
        model.saveMatrixData(model.getMatrixData())
    }

    override fun saveRecord() {
        model.saveRecord(model.getHighestScore())
    }

    override fun getRecords() {
        view.illustrateScores(model.getPrevRecord(), model.getBestRecord())
    }

    override fun refreshData() {
        model.refreshData()
        view.illustrateMatrix(model.getMatrixData())
    }

    override fun swipeSideLeft() {
        model.moveLeft()
        view.illustrateMatrix(model.getMatrixData())
        saveRecord()
        getRecords()
    }

    override fun swipeSideRight() {
        model.moveRight()
        view.illustrateMatrix(model.getMatrixData())
        saveRecord()
        getRecords()
    }

    override fun swipeSideUp() {
        model.moveUp()
        view.illustrateMatrix(model.getMatrixData())
        saveRecord()
        getRecords()
    }

    override fun swipeSideDown() {
        model.moveDown()
        view.illustrateMatrix(model.getMatrixData())
        saveRecord()
        getRecords()
    }
}