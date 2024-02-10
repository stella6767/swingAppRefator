package com.stella.shooting.model

import com.stella.shooting.config.toImageIcon
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

    private val explosionIcon = "/images/explosion.gif".toImageIcon(this::class.java)

    override fun move() {

        if (isCollision) {
            y = (-100).toDouble()
        }

        Thread.sleep(10)

        x -= cos(Math.toRadians(this.angel)) * speed
        y -= sin(Math.toRadians(this.angel)) * speed


        if (x > 1000 || x < -500 || y < -1000 || y > 1000) {
            // map 바깥으로 나가면
            Thread.sleep(100)
            isLife = false
        }

    }

    override fun crush() {
        this.y = -500.0
    }







}