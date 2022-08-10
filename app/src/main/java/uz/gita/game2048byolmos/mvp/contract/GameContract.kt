package uz.gita.game2048byolmos.mvp.contract

interface GameContract {
    interface Model {
        fun getMatrixData() : Array<Array<Int>>
        fun saveMatrixData(data: Array<Array<Int>>)
        fun refreshData()
        fun getHighestScore(): Int
        fun saveRecord(score: Int)
        fun getBestRecord(): String
        fun getPrevRecord(): String
        fun moveLeft()
        fun moveRight()
        fun moveUp()
        fun moveDown()
    }

    interface View {
        fun illustrateMatrix(matrix : Array<Array<Int>>)
        fun illustrateScores(prev: String, best: String)
    }

    interface Presenter {
        fun startPlay()
        fun saveData()
        fun saveRecord()
        fun getRecords()
        fun refreshData()
        fun swipeSideLeft()
        fun swipeSideRight()
        fun swipeSideUp()
        fun swipeSideDown()
    }
}