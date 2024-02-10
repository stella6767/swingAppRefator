package com.stella.shooting.model

import java.awt.Image
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
) : Bullet, Runnable {


    override fun run() {

        while (isLife){
            Thread.sleep(10)
            move()
            if (x > 1000 || x < -500 || y < -500 || y > 1000) {
                this.isLife = false
            }
        }

    }

    override fun fire() {
        // 총알 발사
        val thread = Thread(this)
        thread.start()
        Thread.sleep(100)
    }

    override fun move(){
        x -= cos(Math.toRadians(angel)) * speed
        y -= sin(Math.toRadians(angel)) * speed
    }


    fun crashToPlayer(player: PlayerUnit) { // 적 총알이 아군 비행기에 부딪쳤을 시 충돌연산

        if (!player.isInvincible){
            isCollision = (abs(((player.x - 11) + player.width / 3) - (x + width / 3)) < (width / 3 + player.width / 3)
                    && abs(((player.y - 5) + player.height / 3) - (y + height / 3)) < (height / 3 + player.height / 3))
        }

    }


}