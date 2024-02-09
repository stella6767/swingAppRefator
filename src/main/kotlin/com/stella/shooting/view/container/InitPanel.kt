package com.stella.shooting.view.container

import com.stella.shooting.config.PanelName
import com.stella.shooting.config.SCREEN_HEIGHT
import com.stella.shooting.config.SCREEN_WIDTH
import com.stella.shooting.config.toImageIcon
import java.awt.Graphics
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.ImageIcon
import javax.swing.JPanel

class InitPanel(
    val gameFrame: GameFrame
) : JPanel() {


//    val resource =
//        this::class.java.getResource("/images/GameTitle.gif")

    private val titleIcon = "/images/GameTitle.gif".toImageIcon(this::class.java)

    init {
        layout = null
        keyListener()
    }

    override fun paintComponent(g: Graphics) {
        g.drawImage(titleIcon.image, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this )
    }


    fun keyListener(){
        gameFrame.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_ENTER -> gameFrame.change(PanelName.SELECTAPI)
                }
            }
        })

    }





}