package com.stella.shooting.config

import java.awt.Image
import javax.swing.ImageIcon
import kotlin.reflect.KClass


fun String.toImageIcon(kClass: KClass<*>, width: Int, height: Int): ImageIcon {
    val imageIcon = ImageIcon(kClass.java.getResource(this))
    return ImageIcon(imageIcon.image.getScaledInstance(width, height, Image.SCALE_SMOOTH))
}

fun String.toImageIcon(java: Class<*>): ImageIcon {
    return  ImageIcon(java.getResource(this))
}

