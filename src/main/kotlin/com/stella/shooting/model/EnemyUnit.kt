package com.stella.shooting.model

import java.awt.Image
import javax.swing.ImageIcon

class EnemyUnit(
    override val x: Int,
    override var y: Int,
    override val width: Int,
    override val height: Int,
    var image: ImageIcon,
    override val life: Int = 1,
    override var isLife: Boolean = true,
) : Unit {


    val isCollision: Boolean = false
    fun move() {
        y++
    }



}