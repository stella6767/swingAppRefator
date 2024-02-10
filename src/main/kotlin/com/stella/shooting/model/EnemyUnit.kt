package com.stella.shooting.model

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import javax.swing.ImageIcon

class EnemyUnit(
    override var x: Int,
    override var y: Int,
    override val width: Int,
    override val height: Int,
    val kind: EnemyKind,
    override var isLife: Boolean = true,
) : Unit {


    var isCollision: Boolean = false
    override var life: Int = kind.life
    var image: ImageIcon = kind.icon.toImageIcon(this::class.java)

    var bulletSpeed: Int = 500 // 총알 발사 속도조절
    val bullets = mutableListOf<EnemyBullet>()


    init {
        addBullet(kind.bulletSize)
    }

    fun move() {
        kind.moveFunc(this)
    }

    fun explosion(image: ImageIcon) {
        if (this.isCollision || this.life < 1) {
            this.image = image
            this.isLife = false
            Thread.sleep(500)
            this.y = 1000 // 맵 바깥으로 적 던짐
        }
    }


    fun fire() {

        for (bullet in bullets) {
            println("fire  " + "  ${kind.name}")
            bullet.fire()
        }

    }


    private fun addBullet(size: Int) {
        for (i in 1..size) {
            val bullet = EnemyBullet(
                x + 20.0, y + 40.0,
                300.0, 2.0,
                30, 30,
                kind.bulletImg.toImageIcon(this::class.java).image
            )
            bullets.add(bullet)
        }


    }


}