package com.stella.shooting.model

import java.awt.Graphics
import java.awt.Image
import javax.swing.ImageIcon
import kotlin.math.cos
import kotlin.math.sin

class PlayerBullet(
    override val image: ImageIcon,
    override var x: Double,
    override var y: Double,
    override var angel: Double,
    override var speed: Double,
    override var width: Int = 20,
    override var height: Int = 20,
    override var isLife: Boolean = true,
    override val isCollision: Boolean = false,
) : Bullet {

    override fun move() {

        if (isCollision) {
            y = (-100).toDouble()
        }

        Thread.sleep(10)

        x -= cos(Math.toRadians(this.angel)) * speed
        y -= sin(Math.toRadians(this.angel)) * speed




        if (x > 1000 || x < -500 || y < -100 || y > 1000) {
            // map 바깥으로 나가면
            isLife = false
        }

    }

    override fun fire() {

    }


//    fun crash() { // 플레이어 총알이 보스에 부딪쳤을 시 충돌연산
//        collision = if (Math.abs(((boss.getX()) + boss.getWidth() / 2) - (x + width / 3)) < (width / 3 + boss.getWidth() / 3)
//                && Math.abs(((boss.getY()) + boss.getHeight() / 2) - (y + height / 3)) < (height / 3
//                        + boss.getHeight() / 3)
//            ) {
//                true
//            } else {
//                false
//            }
//    }


}