package uz.gita.game2048byolmos.data.local

import android.content.Context
import android.content.SharedPreferences
import uz.gita.game2048byolmos.data.repository.AppRepository
import uz.gita.game2048byolmos.data.repository.impl.AppRepositoryImpl

class SharedPref {
    // Singleton
    companion object {
        private var sharedPref: SharedPref?= null
        private var preferences: SharedPreferences?=null

        fun init(context: Context) {
            if(sharedPref != null) return
            sharedPref = SharedPref()
            preferences = context.getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        }

        fun getSharedPref(): SharedPref {
            return sharedPref!!
        }
    }

    fun saveRecord(score: Int) {
        preferences!!.edit().putInt("PREVIOUS", score).apply()

        if(preferences!!.getInt("BEST", 0) == 0 || preferences!!.getInt("BEST", 0) < score){
            preferences!!.edit().putInt("BEST", score).apply()
        }
    }

    fun saveMatrixData(data: Array<Array<Int>>) {
        val sb = StringBuilder()
        for(i in data.indices) {
            for(j in 0 until data[i].size) {
                sb.append(data[i][j]).append(",")
            }
        }
        preferences!!.edit().putString("MATRIX", sb.toString())
    }

    fun getSavedMatrixData(): String {
        val matrixString = preferences!!.getString("MATRIX", "")

        return matrixString!!
    }

    fun getPrevRecord(): String = (preferences!!.getInt("PREVIOUS", 0)).toString()
    fun getBestRecord(): String = (preferences!!.getInt("BEST", 0)).toString()

}