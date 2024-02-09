package com.stella.shooting.config

import javax.swing.ImageIcon

enum class PlayerKind(
    val playIcon: ImageIcon,
    val bigPlayIcon: ImageIcon,
    val detailIcon: ImageIcon,

) {
    PLANE1(ImageIcon("images/PlayerPlane1.png"), ImageIcon("images/BigPlane1.png"), ImageIcon("images/PlaneDetailImg1.png")),
    PLANE2(ImageIcon("images/PlayerPlane2.png"), ImageIcon("images/BigPlane2.png"), ImageIcon("images/PlaneDetailImg2.png")),
    PLANE3(ImageIcon("images/PlayerPlane3.png"), ImageIcon("images/BigPlane3.png"), ImageIcon("images/PlaneDetailImg3.png"))
}