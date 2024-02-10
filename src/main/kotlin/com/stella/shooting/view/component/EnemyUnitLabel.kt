package com.stella.shooting.view.component

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.EnemyUnit
import com.stella.shooting.model.PlayerUnit
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JLabel
import kotlin.math.abs

class EnemyUnitLabel(
    private val enemy: EnemyUnit,
    private val playerLabel: PlayerLabel,
) : JLabel(), Runnable {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)

    init {
        icon = enemy.image
        val thread = Thread(this)
        thread.name = enemy.kind.name + this.hashCode()
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

            crushToPlayer()


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
//    }


    private fun crushToPlayer() {

        if (abs((playerLabel.x + playerLabel.width / 2) - (x + playerLabel.width / 2)) < (width / 2
                    + playerLabel.width / 2)
            && abs((playerLabel.y + playerLabel.height / 2) - (y + height / 2)) < (height / 2
                    + playerLabel.height / 2)
            && !playerLabel.player.isInvincible

        ) {
            println("충돌 확인")

            //Thread.sleep을 플레이어에게 걸면 안 되니 임시방편 여기로
            val player = playerLabel.player

            player.isCollision = true
            enemy.isCollision = true

            enemy.explosion(explosionIcon)


            if (player.isCollision) {
                // 충돌후 이미지 변경 및 목숨카운트
                playerLabel.setIcon(explosionIcon)
                player.isInvincible = true
                Thread.sleep(100)

                playerLabel.setIcon(player.playerInvincibleIcon)
                //player.life--
                println("남은 목숨==>${player.life}")
                player.respon()
                Thread.sleep(1000)

                playerLabel.setIcon(player.icon)
                player.isInvincible = false
                player.isCollision = false
            }

            repaint()
        }
    }


    fun enemyDraw(g: Graphics) {
        g.drawImage(enemy.image.image, enemy.x, enemy.y, enemy.width, enemy.height, null)
    }


}