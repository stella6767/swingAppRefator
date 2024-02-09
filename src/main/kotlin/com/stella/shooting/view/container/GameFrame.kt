package com.stella.shooting.view.container

import com.stella.shooting.config.GAME_TITLE
import com.stella.shooting.config.PanelName
import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame

class GameFrame : JFrame(

) {


    private var isPlaying = false // 게임실행 여부
    var gamePanel: GamePanel? = null // 인게임 패널 이거 잘 봐야된다. 오류 !!
    var gameTitlePanel: GameTitlePanel? = null // 타이틀 인트로 패널


    init {
        init()
        setting()
        //listener()

        isVisible = true
    }

    private fun init() {
        change(PanelName.INITTITLE) // 초기 타이틀 화면
    }

    private fun setting() {
        title = GAME_TITLE
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT)
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
    }

    // 패널 바꾸기 함수
    private fun change(panelName: PanelName) {

        when (panelName) {
            PanelName.INITTITLE -> {
                gameTitlePanel = GameTitlePanel(this)
                contentPane.removeAll()
                contentPane.add(gameTitlePanel)
                revalidate()
                repaint()
            }

            PanelName.SELECTAPI -> {

            }
            PanelName.GAMEMAP -> {

            }

        }


//        if (panelName == PanelName.INITTITLE) {
//            gameTitlePanel = GameTitlePanel(gameFrame)
//            contentPane.removeAll()
//            contentPane.add(gameTitlePanel)
//            revalidate()
//            repaint()
//        } else if (panelName == PanelName.SELECTAPI) {
//            selectAPI = SelectAPI(gameFrame)
//            contentPane.removeAll()
//            contentPane.add(selectAPI)
//            revalidate()
//            repaint()
//        } else if (panelName == PanelName.GAMEMAP) {
//            gamePanel = GamePanel(gameFrame)
//            contentPane.removeAll()
//            contentPane.add(gamePanel)
//            revalidate()
//            repaint()
//        } else {
//            gameTitlePanel = null
//            selectAPI = null
//            gamePanel = null
//            isPlaying = false
//            contentPane.removeAll()
//            revalidate()
//            repaint()
//        }
    }


}