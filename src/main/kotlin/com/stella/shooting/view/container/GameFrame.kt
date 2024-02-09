package com.stella.shooting.view.container

import com.stella.shooting.config.GAME_TITLE
import com.stella.shooting.config.PanelName
import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import javax.swing.JFrame
import javax.swing.JPanel

class GameFrame : JFrame(

) {

    private lateinit var jPanel: JPanel
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
    fun change(panelName: PanelName) {

        when (panelName) {
            PanelName.INITTITLE -> {
                jPanel = InitPanel()
                contentPane.removeAll()
                contentPane.add(jPanel)
                revalidate()
                repaint()
            }
            PanelName.SELECTAPI -> {
                val selectPanel = SelectPanel(this)
                contentPane.removeAll()
                contentPane.add(selectPanel)
                revalidate()
                repaint()
            }
            PanelName.INGAME -> {

            }

        }

    }


//    private fun listener() {
//        addKeyListener(object : KeyAdapter() {
//            override fun keyPressed(e: KeyEvent) {
//                when (e.keyCode) {
//                    KeyEvent.VK_1 -> player.setWepponLevelUp(true)
//                    KeyEvent.VK_ENTER -> change(PanelName.SELECTAPI)
//                    KeyEvent.VK_SPACE -> player.setAttack(true)
//                    KeyEvent.VK_UP -> player.setUp(true)
//                    KeyEvent.VK_DOWN -> player.setDown(true)
//                    KeyEvent.VK_LEFT -> player.setLeft(true)
//                    KeyEvent.VK_RIGHT -> player.setRight(true)
//                }
//            }
//
//            override fun keyReleased(e: KeyEvent) {
//                when (e.keyCode) {
//                    KeyEvent.VK_1 -> player.setWepponLevelUp(false)
//                    KeyEvent.VK_SPACE -> player.setAttack(false)
//                    KeyEvent.VK_UP -> player.setUp(false)
//                    KeyEvent.VK_DOWN -> player.setDown(false)
//                    KeyEvent.VK_LEFT -> player.setLeft(false)
//                    KeyEvent.VK_RIGHT -> player.setRight(false)
//                }
//            }
//        })
//    }


}