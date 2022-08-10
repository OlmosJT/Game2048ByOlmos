package uz.gita.game2048byolmos.mvp.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.game2048byolmos.R
import uz.gita.game2048byolmos.data.repository.SlideEnum
import uz.gita.game2048byolmos.databinding.ScreenGameBinding
import uz.gita.game2048byolmos.mvp.contract.GameContract
import uz.gita.game2048byolmos.mvp.presenter.GamePresenter
import uz.gita.game2048byolmos.utils.MyTouchListener
import uz.gita.game2048byolmos.utils.getBackroundByAmount

class GameScreen: Fragment(R.layout.screen_game), GameContract.View {
    private val presenter: GameContract.Presenter = GamePresenter(this)
    private val buttons: MutableList<TextView> = ArrayList(16)
    private val binding by viewBinding(ScreenGameBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadViews(view)
        presenter.startPlay()
        presenter.getRecords()

        binding.btnRestart.setOnClickListener {
            // save
            presenter.saveRecord()
            presenter.getRecords()
            // refresh
            presenter.refreshData()
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun loadViews(view: View) {
        val mainView: LinearLayoutCompat = view.findViewById(R.id.mainView)
        for (i in 0 until mainView.childCount) {
            val line: LinearLayoutCompat = mainView.getChildAt(i) as LinearLayoutCompat
            for (j in 0 until line.childCount) {
                buttons.add(line.getChildAt(j) as TextView)
            }
        }
        val myTouchListener = MyTouchListener(requireContext())
        myTouchListener.setResultListener {
            when (it) {
                SlideEnum.DOWN -> presenter.swipeSideDown()
                SlideEnum.UP -> presenter.swipeSideUp()
                SlideEnum.LEFT -> presenter.swipeSideLeft()
                SlideEnum.RIGHT -> presenter.swipeSideRight()
            }
        }
        mainView.setOnTouchListener(myTouchListener)
    }

    override fun illustrateMatrix(matrix: Array<Array<Int>>) {
        for (i in matrix.indices) {
            for (j in 0 until matrix[i].size) {
                if (matrix[i][j] == 0) buttons[4 * i + j].text = ""
                else buttons[4 * i + j].text = matrix[i][j].toString()
                buttons[4 * i + j].setBackgroundResource(matrix[i][j].getBackroundByAmount())
            }
        }
    }

    override fun illustrateScores(prev: String, best: String) {
        binding.prevScore.text = prev
        binding.bestScore.text = best
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.saveData()
    }


}