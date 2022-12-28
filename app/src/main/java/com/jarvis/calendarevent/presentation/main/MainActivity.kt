@file:Suppress("SameParameterValue")

package com.jarvis.calendarevent.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.viewModels
import com.jarvis.calendarevent.R
import com.jarvis.calendarevent.common.click
import com.jarvis.calendarevent.databinding.ActivityMainBinding
import com.jarvis.calendarevent.presentation.base.BaseActivity
import com.jarvis.calendarevent.presentation.common.pref.AppPreferenceKey
import com.jarvis.calendarevent.presentation.home.GameEngine
import com.jarvis.calendarevent.presentation.home.GameEngineProtocol
import com.jarvis.calendarevent.presentation.home.GameMoves
import com.jarvis.calendarevent.presentation.home.TileMoveType
import com.jarvis.calendarevent.presentation.home.model.Model2048
import com.jarvis.design_system.textview.CustomTextView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::inflate),
    GameEngineProtocol {
    private val viewModel: MainViewModel by viewModels()


    private var x1 = 0f
    private var y1 = 0f

    private lateinit var game: Model2048
    private var highScore: Int = 0
    private val cells = IntArray(16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this

        cells[0] = R.id.index0
        cells[1] = R.id.index1
        cells[2] = R.id.index2
        cells[3] = R.id.index3
        cells[4] = R.id.index4
        cells[5] = R.id.index5
        cells[6] = R.id.index6
        cells[7] = R.id.index7
        cells[8] = R.id.index8
        cells[9] = R.id.index9
        cells[10] = R.id.index10
        cells[11] = R.id.index11
        cells[12] = R.id.index12
        cells[13] = R.id.index13
        cells[14] = R.id.index14
        cells[15] = R.id.index15

        if (savedInstanceState != null) {
            val tmpGame: Model2048? = savedInstanceState.getSerializable(GAME_KEY) as Model2048?
            if (tmpGame != null) {
                game = tmpGame
                game.replotBoard()
            }
        } else {
            game = Model2048(this)
            this.setupNewGame()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("Logm", "Inside onSaveInstanceState method")
        outState.putSerializable(GAME_KEY, game)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i("Logm", "Inside onRestoreInstanceState method")
        val tmpGame: Model2048? = savedInstanceState.getSerializable(GAME_KEY) as Model2048?
        if (tmpGame != null) {
            game = tmpGame
            game.replotBoard()
        }
    }

    // Start / Reset game here ->
    private fun setupNewGame() {
        this.highScore = appPreference?.get(AppPreferenceKey.SCORE, Int::class.java) ?: 0
        //this.gamePanel.resetBoard()
        game.newGame(this.highScore)
        this.userScoreChanged(0)
    }

    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    override fun onTouchEvent(event: MotionEvent): Boolean {

        when (event.action) {
            // when user first touches the screen we get x and y coordinate
            MotionEvent.ACTION_DOWN -> {
                x1 = event.x
                y1 = event.y
            }
            MotionEvent.ACTION_UP -> {
                val x2 = event.x
                val y2 = event.y
                val minDistance = 200

                if (x1 < x2 && x2 - x1 > minDistance) {
                    game.actionMove(GameMoves.Right)
                } else if (x1 > x2 && x1 - x2 > minDistance) {
                    game.actionMove(GameMoves.Left)
                } else if (y1 < y2 && y2 - y1 > minDistance) {
                    game.actionMove(GameMoves.Down)
                } else if (y1 > y2 && y1 - y2 > minDistance) {
                    game.actionMove(GameMoves.Up)
                }
            }
        }
        return super.onTouchEvent(event)
    }

    private fun paintTransition(move: GameEngine.Transition) {

        val tv = findViewById<CustomTextView>(cells[move.location])

        if (move.action == TileMoveType.Slide || move.action == TileMoveType.Merge) {
            //Log.i("Logm", "About to do paint compact")
            //Log.i("Logm", "About to do paint compact w=$w h=$h t=$t")
            paintCell(tv, move.value)
            this.paintTransition(GameEngine.Transition(TileMoveType.Clear, 0, move.oldLocation))
        } else {
            paintCell(tv, move.value)
        }
    }

    private fun paintCell(obj: Any, value: Int) {

        val tv = obj as CustomTextView
        tv.text = if (value <= 0) "" else "$value"

        var txCol = resources.getColor(R.color.t_dark_text, null)
        var bgCol = resources.getColor(R.color.t0_bg, null)

        when (value) {
            2 -> bgCol = resources.getColor(R.color.t2_bg, null)
            4 -> bgCol = resources.getColor(R.color.t4_bg, null)
            8 -> {
                txCol = resources.getColor(R.color.t_light_text, null)
                bgCol = resources.getColor(R.color.t8_bg, null)
            }
            16 -> {
                txCol = resources.getColor(R.color.t_light_text, null)
                bgCol = resources.getColor(R.color.t16_bg, null)
            }
            32 -> {
                txCol = resources.getColor(R.color.t_light_text, null)
                bgCol = resources.getColor(R.color.t32_bg, null)
            }
            64 -> {
                txCol = resources.getColor(R.color.t_light_text, null)
                bgCol = resources.getColor(R.color.t64_bg, null)
            }
            128 -> {
                txCol = resources.getColor(R.color.t_light_text, null)
                bgCol = resources.getColor(R.color.t128_bg, null)
            }
            256 -> bgCol = resources.getColor(R.color.t256_bg, null)
            512 -> bgCol = resources.getColor(R.color.t512_bg, null)
            1024 -> bgCol = resources.getColor(R.color.t1024_bg, null)
            2048 -> bgCol = resources.getColor(R.color.t2048_bg, null)
            else -> {} // won't happen
        }
        tv.setBackgroundColor(bgCol)
        tv.setTextColor(txCol)
    }

    override fun userWin() {
        Toast.makeText(
            this, resources.getString(R.string.winner_toast_message) +
                    game.maxTile, Toast.LENGTH_LONG
        ).show()
    }

    override fun userFail() {
        Toast.makeText(
            this, resources.getString(R.string.lose_toast_message),
            Toast.LENGTH_LONG
        ).show()
    }

    override fun userPB(score: Int) {
        Toast.makeText(
            this, resources.getString(R.string.personalbest_toast_message),
            Toast.LENGTH_SHORT
        ).show()
        appPreference?.putSync(AppPreferenceKey.SCORE, score)
        this.highScore = score
    }

    override fun userScoreChanged(score: Int) {
        binding.viewGame.scoreTitle.text =
            StringBuffer(resources.getString(R.string.score)).append(score)
        binding.viewGame.highTitle.text = StringBuffer(resources.getString(R.string.high)).append(
            appPreference?.get(AppPreferenceKey.SCORE, Int::class.java) ?: 0
        )
    }

    override fun updateTileValue(move: GameEngine.Transition) {
        paintTransition(move)
    }

    companion object {
        private const val GAME_KEY = "2048_GAME_KEY"
    }

    override fun setUpViews() {
        binding.viewGame.buttonNewGame.click {
            setupNewGame()
        }
    }


    override fun observeData() {
        super.observeData()

    }
}
