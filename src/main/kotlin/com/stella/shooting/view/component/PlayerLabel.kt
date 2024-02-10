package com.stella.shooting.view.component

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.PlayerUnit
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    val player: PlayerUnit
) : JLabel(), Runnable {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)

    init {
        icon = player.icon
    }

    override fun run() {

        while (player.isLife) {
            Thread.sleep(5)
            player.gameOver()
            player.keyProcess()
            player.playerAttackProcess()


//            PlayerBullet()
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


    fun paintBullet(g: Graphics) {
        // PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
        for (bullet in player.bullets) {
            g.drawImage(bullet.image, bullet.x.toInt(), bullet.y.toInt(), bullet.width, bullet.height, null)
        }
    }




}