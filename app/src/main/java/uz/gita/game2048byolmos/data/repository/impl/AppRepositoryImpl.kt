package uz.gita.game2048byolmos.data.repository.impl

import android.content.Context
import android.util.Log
import android.widget.Toast
import uz.gita.game2048byolmos.data.local.SharedPref
import uz.gita.game2048byolmos.data.repository.AppRepository
import kotlin.random.Random

class AppRepositoryImpl private constructor(val context: Context): AppRepository {
    // Singleton
    companion object {
        private var obj: AppRepository ?= null

        fun init(context: Context) {
            if(obj != null) return
            obj = AppRepositoryImpl(context)
        }

        fun getAppRepository(): AppRepository {
            return obj!!
        }
    }
    //----------------------------------------------------

    private var matrix: Array<Array<Int>> =
        arrayOf(
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0)
        )

    //----------------------------------------------------
    private var ADD_ELEMENT = 2
    //----------------------------------------------------
    init {
        addNewElementToMatrix()
        addNewElementToMatrix()
    }

    override fun refreshData() {
        matrix = arrayOf(
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0),
            arrayOf(0,0,0,0)
        )
        addNewElementToMatrix()
        addNewElementToMatrix()
    }

    private fun addNewElementToMatrix() {
        val coordinates = ArrayList<Pair<Int, Int>>()
        for (i in matrix.indices) { // for(i in 0 until matrix.size)
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) {
                    coordinates.add(Pair(i, j))
                }
            }
        }
        if (coordinates.isEmpty()){
            Toast.makeText(context, "Game over", Toast.LENGTH_SHORT).show()
        }else{
            val randomIndex = Random.nextInt(0,coordinates.size)
            matrix[coordinates[randomIndex].first][coordinates[randomIndex].second] = ADD_ELEMENT
        }
    }

    override fun getMatrixData(sharedPref: SharedPref): Array<Array<Int>> {
        val savedMatrix = sharedPref.getSavedMatrixData()
        Log.d("TTT", "matrix: $savedMatrix")
        var index: Int = 0;
        if(savedMatrix.isNotEmpty()) {
            savedMatrix.split(",").apply {
                for(i in matrix.indices) {
                    for(j in matrix[i].indices) {
                        matrix[i][j] = Integer.parseInt(this[index++])
                    }
                }
            }
        }
        return matrix
    }
    override fun getHighestScore(): Int {
        var score = 0
        for(i in matrix.indices){
            for (j in 0 until matrix[i].size){
                if(matrix[i][j] > score) score = matrix[i][j]
            }
        }
        return score
    }

    override fun moveLeft() {
        for (i in matrix.indices) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[i][j])
                else {
                    if (amount.last() == matrix[i][j] && bool) {
                        amount[amount.size-1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[i][j])
                    }
                }
                matrix[i][j] = 0
            }
            for (j in 0 until amount.size) {
                matrix[i][j] = amount[j]
            }
        }
        addNewElementToMatrix()
    }

    override fun moveRight() {
        for (i in matrix.indices) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].size-1 downTo 0) {
                if (matrix[i][j] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[i][j])
                else {
                    if (amount.last() == matrix[i][j] && bool) {
                        amount[amount.size-1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[i][j])
                    }
                }
                matrix[i][j] = 0
            }
            for (k in 0 until amount.size) {
                matrix[i][3-k] = amount[k]
            }
        }
        addNewElementToMatrix()
    }

    override fun moveUp() {
        for (i in matrix.indices)  {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].indices) {
                if (matrix[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[j][i])
                else {
                    if (amount.last() == matrix[j][i] && bool) {
                        amount[amount.size-1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[j][i])
                    }
                }
                matrix[j][i] = 0
            }
            for (j in 0 until amount.size) {
                matrix[j][i] = amount[j]
            }
        }
        addNewElementToMatrix()
    }

    override fun moveDown() {
        for (i in matrix.indices) {
            val amount = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].size-1 downTo 0) {
                if (matrix[j][i] == 0) continue
                if (amount.isEmpty()) amount.add(matrix[j][i])
                else {
                    if (amount.last() == matrix[j][i] && bool) {
                        amount[amount.size-1] = amount.last() * 2
                        bool = false
                    } else {
                        bool = true
                        amount.add(matrix[j][i])
                    }
                }
                matrix[j][i] = 0
            }
            for (j in  amount.size-1 downTo  0) {
                matrix[3-j][i] = amount[j]
            }
        }
        addNewElementToMatrix()
    }
}