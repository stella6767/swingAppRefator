package com.stella.shooting.view.component

import com.stella.shooting.config.SwingComponentBehavior
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.PlayerUnit
import com.stella.shooting.view.container.GamePanel
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    val player: PlayerUnit,
    val gamePanel: GamePanel
) : JLabel(), Runnable, SwingComponentBehavior {

    init {
        icon = player.icon
        val thread = Thread(this)
        thread.name = "player"
        thread.start()
    }

    override fun run() {
        while (player.isLife) {
            Thread.sleep(5)
            gameOver()
            player.keyProcess()
            player.fireListner()

            setComponent()
        }
    }

    override fun setComponent() {
        setLocation(player.x, player.y) // repaint()
        setSize(player.width, player.height)
    }

    private fun gameOver() {
        if (player.life < 1) {
            player.isLife = false
            gamePanel.isPlaying = false
        }
    }





}