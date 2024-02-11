package com.stella.shooting.view.component

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.SwingComponentBehavior
import com.stella.shooting.config.toImageIcon
import com.stella.shooting.model.EnemyUnit
import com.stella.shooting.view.container.GamePanel
import javax.swing.JLabel
import kotlin.math.abs

class EnemyUnitLabel(
    private val enemy: EnemyUnit,
    private val playerLabel: PlayerLabel,
) : JLabel(), Runnable, SwingComponentBehavior {

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class, enemy.width, enemy.height)

    init {
        icon = enemy.image
        enemy.gameFrame.panel.add(this)
        val thread = Thread(this)
        thread.name = enemy.kind.name + this.hashCode()
        thread.start()
    }

    override fun run() {

        while (enemy.isLife) {
            Thread.sleep(10)
            explosion()
            enemy.move()
            setComponent()

            if (enemy.y > 900) {
                println(Thread.currentThread().name + " terminated")
                enemy.isLife = false
            }

            enemy.createBulletsByKind(playerLabel)
            crushToPlayer()
            crushToPlayerBullet()
        }

    }

    override fun setComponent() {
        setLocation(enemy.x, enemy.y)
        setSize(enemy.width, enemy.height)
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

            enemy.life--

            if (player.isCollision) {
                // 충돌후 이미지 변경 및 목숨카운트
                playerLabel.setIcon(explosionIcon)
                player.life--

                player.isInvincible = true
                Thread.sleep(100)
                playerLabel.setIcon(player.playerInvincibleIcon)
                println("남은 목숨==>${player.life}")
                player.respon()

                playerLabel.setIcon(player.icon)
                player.isInvincible = false
                player.isCollision = false
            }

            repaint()
        }
    }


    private fun explosion() {
        if (enemy.life < 1) {

            enemy.image = explosionIcon
            icon = explosionIcon
            Thread.sleep(50)

            if (enemy.kind == EnemyKind.BOSS) {
                enemy.isLife = false
                Thread.sleep(2000)
                println("보스 처치!!")
                System.exit(1) // 프로그램 종료
            } else {
                enemy.y = 1000 // 맵 바깥으로 적 던짐
            }


        }
    }


    private fun crushToPlayerBullet() {
        val bullets = playerLabel.player.bullets
        for (bullet in bullets) {
            // 적기가 아군총알에 충돌시 구현,
            val isCrash = crash(
                bullet.x.toInt(), bullet.y.toInt(),
                enemy.x, enemy.y,
                bullet.width, bullet.height,
                enemy.width, enemy.height
            )
            if (isCrash && (enemy.life > 0)) {
                bullet.bullet.crush()
                enemy.life--
                //println("적 hp==>${enemy.life}")
                println(bullets.size)
            }
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


}