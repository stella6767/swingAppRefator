package com.stella.shooting.view.component

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.PlayerUnit
import com.stella.shooting.view.container.GamePanel
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    val player: PlayerUnit,
    val gamePanel: GamePanel
) : JLabel(), Runnable {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)

    init {
        icon = player.icon
        val thread = Thread(this)
        thread.name = "player"
        thread.start()
    }

    override fun run() {
        while (player.isLife) {
            Thread.sleep(5)
            player.gameOver()
            player.keyProcess()
            player.fireListner(gamePanel)

            setLocation(player.x, player.y) // repaint()
            setSize(player.width, player.height)
        }
    }

    private fun checkCrush() {

        if (player.isCollision) {
            // 충돌후 이미지 변경 및 목숨카운트
            setIcon(explosionIcon)
            player.isInvincible = true
            Thread.sleep(100)

            setIcon(player.playerInvincibleIcon)
            //player.life--
            println("남은 목숨==>${player.life}")
            player.respon()

            setIcon(player.icon)
            player.isInvincible = false
            player.isCollision = false

            repaint()
        }
    }




}