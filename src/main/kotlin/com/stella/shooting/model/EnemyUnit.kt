package com.stella.shooting.model

import com.stella.shooting.config.EnemyKind
import com.stella.shooting.config.toImageIcon
import java.awt.Image
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



    fun move() {
        kind.moveFunc(this)
    }


    fun explosion(image: ImageIcon){
        if (this.isCollision || this.life < 1){
            this.image = image
            this.isLife = false
            Thread.sleep(500)
            this.y = 1000 // 맵 바깥으로 적 던짐
        }
    }



}