package com.stella.shooting.model

import java.awt.Image

interface Bullet {

    var x: Double
    var y: Double
    var angel: Double
    var speed: Double
    var width: Int
    var height: Int
    var isLife: Boolean
    val collision: Boolean //충돌여부
    val image: Image

    fun fire()
}