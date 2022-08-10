package uz.gita.game2048byolmos.mvp.model

import uz.gita.game2048byolmos.data.local.SharedPref
import uz.gita.game2048byolmos.data.repository.AppRepository
import uz.gita.game2048byolmos.data.repository.impl.AppRepositoryImpl
import uz.gita.game2048byolmos.mvp.contract.GameContract

class GameModel: GameContract.Model {
    private val repository : AppRepository = AppRepositoryImpl.getAppRepository()
    private val sharedPref: SharedPref = SharedPref.getSharedPref()

    override fun getMatrixData(): Array<Array<Int>> {
        return repository.getMatrixData(sharedPref)
    }

    override fun saveMatrixData(data: Array<Array<Int>>) {
        sharedPref.saveMatrixData(data)
    }

    override fun refreshData() {
        return repository.refreshData()
    }

    override fun moveLeft() {
        return repository.moveLeft()
    }

    override fun moveRight() {
        return repository.moveRight()
    }

    override fun moveUp() {
        return repository.moveUp()
    }

    override fun moveDown() {
        return repository.moveDown()
    }

    override fun getHighestScore(): Int {
        return repository.getHighestScore()
    }

    override fun saveRecord(sc: Int) {
        return sharedPref.saveRecord(sc)
    }

    override fun getBestRecord(): String {
        return sharedPref.getBestRecord()
    }

    override fun getPrevRecord(): String {
        return sharedPref.getPrevRecord()
    }
}