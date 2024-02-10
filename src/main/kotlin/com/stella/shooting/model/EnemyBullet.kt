package com.stella.shooting.model

import com.stella.shooting.config.toImageIcon
import com.stella.shooting.view.component.PlayerLabel
import javax.swing.ImageIcon
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

class EnemyBullet(
    override var x: Double,
    override var y: Double,
    override var angel: Double,
    override var speed: Double,
    override var width: Int,
    override var height: Int,
    override val image: ImageIcon,
    override var isLife: Boolean = true,
    override var isCollision: Boolean = false,
    private val playerLabel: PlayerLabel,
) : Bullet {

    val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)
    val player = playerLabel.player

    override fun move() {

        Thread.sleep(10)
        crashToPlayer()

        x -= cos(Math.toRadians(angel)) * speed
        y -= sin(Math.toRadians(angel)) * speed

        if (x > 1000 || x < -500 || y < -500 || y > 1000) {
            Thread.sleep(500)
            this.isLife = false
        }
    }


    override fun crush() {

    }


    private fun crashToPlayer() { // 적 총알이 아군 비행기에 부딪쳤을 시 충돌연산
        if (!player.isInvincible) {
            isCollision = (abs(((player.x - 11) + player.width / 3) - (x + width / 3)) < (width / 3 + player.width / 3)
                    && abs(((player.y - 5) + player.height / 3) - (y + height / 3)) < (height / 3 + player.height / 3))
        }
        if (isCollision) {
            explosion() // 충돌 폭발 메서드
        }
    }

    private fun explosion() { // 충돌후 이미지 변경 및 목숨카운트
        playerLabel.setIcon(explosionIcon)
        y = 800.0

        player.life--
        player.isInvincible = true
        Thread.sleep(100)
        playerLabel.setIcon(player.playerInvincibleIcon)
        println("남은 목숨==>${player.life}")

        player.respon()
        player.isInvincible = false
        player.isCollision = false
        playerLabel.repaint()
    }


}