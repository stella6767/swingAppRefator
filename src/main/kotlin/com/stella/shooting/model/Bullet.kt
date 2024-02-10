package com.stella.shooting.model

import java.awt.Image
import javax.swing.ImageIcon
import kotlin.math.cos
import kotlin.math.sin

interface Bullet {

    var x: Double
    var y: Double
    var angel: Double
    var speed: Double
    var width: Int
    var height: Int
    var isLife: Boolean
    val isCollision: Boolean //충돌여부
    val image: ImageIcon

    fun crush()
    fun move()

}