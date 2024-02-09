package com.stella.shooting.view.component

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.EnemyUnit
import com.stella.shooting.model.PlayerUnit
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JLabel

class EnemyUnitLabel(
    private val enemy: EnemyUnit
) : JLabel(), Runnable {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)
    init {

        icon = enemy.image

        val thread = Thread(this)
        thread.name = "enemy"
        thread.start()
    }

    override fun run() {

        while (enemy.isLife) {
            Thread.sleep(10)
            setLocation(enemy.x, enemy.y)
            enemy.move()
            if (enemy.y > 900) {
                println("enemy1MoveThread 종료")
                enemy.isLife = false
            }
        }

    }


//    fun crush() { // 적비행기-Player 충돌
//
//
//        while (!player.isInvincible() && y < 900) {
//
//            if (Math.abs((player.getX() + player.getWidth() / 2) - (x + player.getWidth() / 2)) < (width / 2
//                        + player.getWidth() / 2)
//                && Math.abs((player.getY() + player.getHeight() / 2) - (y + height / 2)) < (height / 2
//                        + player.getHeight() / 2)
//            ) {
//                collision = true
//            } else {
//                collision = false
//            }
//
//
//            if (collision) {
//                explosePlayer(player, enemy1) // 플레이어와 적기 충돌시
//            }
//
//            if (crushCheck) {
//                explosePlayer(enemy1) // 플레이어 총알이 적기 충돌시
//            }
//            Thread.sleep(10)
//
//        }
//
//
//    }


    fun crushToPlayer() {
        enemy.image = explosionIcon
        enemy.y = 1000 // 맵 바깥으로 적 던짐
    }


    fun enemyDraw(g: Graphics) {
        g.drawImage(enemy.image.image, enemy.x, enemy.y, enemy.width, enemy.height, null)
    }


}