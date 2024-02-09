package com.stella.shooting.view.component

import com.stella.shooting.model.PlayerPlane
import java.awt.Graphics
import javax.swing.JLabel

class PlayerLabel(
    val player: PlayerPlane
) : JLabel(), Runnable {

    init {
        icon = player.playerIcon
    }
    override fun run() {

        while (player.isLife) {
            Thread.sleep(10)
            player.gameOver()
            player.keyProcess()
//            playerAttackProcess()
//            PlayerBullet()
            setLocation(player.x, player.y) // repaint()
            setSize(player.width, player.height)
            //player.pCount++ // 1씩 늘어난다
        }

    }

//    override fun paintComponent(g: Graphics) {
//
////        for (i in playerBullets.indices) {
////            playerAttack = playerBullets.get(i)
////            if (select == 1) {
////                g.drawImage(playerAttack.bulletImg1, playerAttack.getX() as Int, playerAttack.getY() as Int, null)
////            }
////            if (select == 2) {
////                g.drawImage(
////                    playerAttack.bulletImg2, playerAttack.getX() as Int, playerAttack.getY() as Int, 20, 20,
////                    null
////                )
////            }
////            if (select == 3) {
////                g.drawImage(
////                    playerAttack.bulletImg3, playerAttack.getX() as Int, playerAttack.getY() as Int, 20, 20,
////                    null
////                )
////            }
////            // PlayaerAttack의 자료형을 double로 두고, drawImage를 돌릴 때만 형변환 해준다 (삼각함수 계산을 위해)
////        }
//    }




}