package com.stella.shooting.config

import java.awt.Image
import java.awt.Toolkit
import java.nio.charset.StandardCharsets
import javax.swing.ImageIcon
import kotlin.reflect.KClass


fun String.toImageIcon(kClass: KClass<*>, width: Int, height: Int): ImageIcon {
    val resource = kClass.java.getResource(this)
    //val image = Toolkit.getDefaultToolkit().getImage(resource)
    val scaledIcon = ImageIcon(resource)
    return ImageIcon(scaledIcon.image.getScaledInstance(width, height, Image.SCALE_DEFAULT))
}

fun String.toImageIcon(java: Class<*>): ImageIcon {
    return  ImageIcon(java.getResource(this))
}

