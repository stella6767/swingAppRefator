package com.stella.shooting.model

interface Unit {
    val width: Int
    val height: Int
    val x:Int
    val y:Int
    val life:Int
    var isLife: Boolean
}