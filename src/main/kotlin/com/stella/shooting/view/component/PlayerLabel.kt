package com.stella.shooting.view.component

import com.stella.shooting.model.PlayerUnit
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    val player: PlayerUnit
) : JLabel(), Runnable {

    init {
        icon = player.playerIcon
    }
    override fun run() {

        while (player.isLife) {
            Thread.sleep(10)
            player.gameOver()
            player.keyProcess()
            player.playerAttackProcess()
//            PlayerBullet()
            setLocation(player.x, player.y) // repaint()
            setSize(player.width, player.height)
            //player.pCount++ // 1씩 늘어난다
        }
    }


    fun paintBullet(g: Graphics){
        for (bullet in player.bullets) {
            g.drawImage(bullet.image, bullet.x.toInt(), bullet.y.toInt(), null)
            // PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
        }
    }





}