package uz.gita.game2048byolmos.data.repository

import uz.gita.game2048byolmos.data.local.SharedPref

interface AppRepository {
    fun getMatrixData(sharedPref: SharedPref) : Array<Array<Int>>
    fun getHighestScore(): Int
    fun refreshData()
    fun moveLeft()
    fun moveRight()
    fun moveUp()
    fun moveDown()
}