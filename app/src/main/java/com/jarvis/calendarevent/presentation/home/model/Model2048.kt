package com.jarvis.calendarevent.presentation.home.model

import com.jarvis.calendarevent.presentation.home.GameEngine
import com.jarvis.calendarevent.presentation.home.GameEngineProtocol
import java.util.*

class Model2048(delegated: GameEngineProtocol) : GameEngine(delegate = delegated) {

    var startedPlaying: Date
        private set
    var startLastGame: Date
        private set

    init {
        this.startedPlaying = Date()
        this.startLastGame = this.startedPlaying
    }

    override fun newGame(newHighScore: Int) {
        super.newGame(newHighScore)
        this.startLastGame = Date()
    }

    fun getTotalTimePlaying() = (Date().time - startedPlaying.time)
    fun getTimePlayingCurrent() = (Date().time - startLastGame.time)
}
