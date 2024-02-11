package com.stella.shooting.view.container

import com.stella.shooting.config.*
import com.stella.shooting.view.component.PlayerLabel
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JPanel

class GameFrame : JFrame(

) {

    lateinit var panel: JPanel

    init {
        init()
        setting()
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
    fun change(
        panelName: PanelName,
        playerKind: PlayerKind? = null
    ) {
        //약간 찝찝하긴 한데..
        this.panel = when (panelName) {
            PanelName.INITTITLE -> InitPanel(this)
            PanelName.SELECTAPI -> SelectPanel(this)
            PanelName.INGAME -> GamePanel(this, playerKind ?: throw IllegalArgumentException("cant find player"))
        }

        contentPane.removeAll()
        contentPane.add(panel)

        revalidate()
        repaint()
    }


    @PreDestroy
    fun preDestroy() {

        println("소멸!!")
    }


}