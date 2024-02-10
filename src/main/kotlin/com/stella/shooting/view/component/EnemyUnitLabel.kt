package com.stella.shooting.view.component

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.Bullet
import com.stella.shooting.model.EnemyUnit
import java.awt.Graphics
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

            enemy.explosion(explosionIcon)

            Thread.sleep(10)
            setLocation(enemy.x, enemy.y)
            enemy.move()
            if (enemy.y > 900) {
                println(Thread.currentThread().name + " terminated")
                enemy.isLife = false
            }

            crushToPlayer()
            crushBullet()
        }

    }


    private fun crushToPlayer() {

        if (abs((playerLabel.x + playerLabel.width / 2) - (x + playerLabel.width / 2)) < (width / 2
                    + playerLabel.width / 2)
            && abs((playerLabel.y + playerLabel.height / 2) - (y + height / 2)) < (height / 2
                    + playerLabel.height / 2)
            && !playerLabel.player.isInvincible

        ) {
            println("충돌 확인")

            //Player에게 Thread.sleep을 걸면 안 되니 임시방편 여기로
            val player = playerLabel.player

            player.isCollision = true
            enemy.isCollision = true


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


    private fun crushBullet(){
        val bullets = playerLabel.player.bullets
        for (bullet in bullets) {
            crushToPlayerBullet(bullet)
        }
    }

    private fun crushToPlayerBullet(bullet: Bullet) { // 적기가 아군총알에 충돌시 구현,

//        enemy.image = explosionIcon
//        println("적기와 아군비행기 총알 충돌")
//        Thread.sleep(1000)
//        enemyUnit.y = 1000 // Thread 강제종료 방법이 마땅히 안 떠오름 대충 이렇게
//        enemyUnit.repaint()

        val isCrash = crash(
            bullet.x.toInt(), bullet.y.toInt(),
            enemy.x, enemy.y,
            bullet.width, bullet.height,
            enemy.width, enemy.height
        )

        if (isCrash && (enemy.life > 0)) {
            //playerBullets.removeAt(i) // 충돌판정이 맞으면, 총알 사라지고 적의 체력이 1 깍임
            //i = i - 1 // 인덱스 에러해결하기 위해
            //enemyUnitList.get(j).setLife(enemyUnitList.get(j).getLife() - 1)
            enemy.life--
            println("적 hp==>${enemy.life}")

        }


    }


    // 플레이어 총알이 적의 비행기에 닿았는지 탐지하는 연산
    private fun crash(
        x1: Int, y1: Int,
        x2: Int, y2: Int,
        w1: Int, h1: Int,
        w2: Int, h2: Int
    ): Boolean {

        // x,y : 위치값 , w,h : 이미지의 높이와 길이.

        val result = (abs(((x1 + w1 / 2) - (x2 + w2 / 2)).toDouble()) < (w2 / 2 + w1 / 2)
                && abs(((y1 + h1 / 2) - (y2 + h2 / 2)).toDouble()) < (h2 / 2 + h1 / 2))

        return result
    }


    fun enemyDraw(g: Graphics) {
        g.drawImage(enemy.image.image, enemy.x, enemy.y, enemy.width, enemy.height, null)
    }


}