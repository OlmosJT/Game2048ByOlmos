package uz.gita.game2048byolmos.utils

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import uz.gita.game2048byolmos.data.repository.SlideEnum
import kotlin.math.abs

class MyTouchListener(context : Context) : View.OnTouchListener {
    private var resultListener : ((SlideEnum) -> Unit)?=null
    private val gesture = GestureDetector(context,MyGestureDetector())

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        gesture.onTouchEvent(event)
        return true
    }

    inner class MyGestureDetector : GestureDetector.SimpleOnGestureListener() {
        private val MIN_MOVE = 100

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            if (abs(e2.x - e1.x) > abs(e2.y - e1.y)) {
                if ( abs(e2.x - e1.x) < MIN_MOVE) return false
                if (e2.x > e1.x) { resultListener?.invoke(SlideEnum.RIGHT) }
                else resultListener?.invoke(SlideEnum.LEFT)
                // horizontal
            } else {
                // vertical
                if ( abs(e2.y - e1.y) < MIN_MOVE) return false
                if (e2.y > e1.y) { resultListener?.invoke(SlideEnum.DOWN) }
                else resultListener?.invoke(SlideEnum.UP)
            }
            return true
        }
    }

    fun setResultListener(block : (SlideEnum) -> Unit) {
        resultListener = block
    }
}