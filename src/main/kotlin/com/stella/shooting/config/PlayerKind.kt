package com.stella.shooting.config

import javax.swing.ImageIcon

enum class PlayerKind(
    val playIcon: String, // 플레이어 기체 이미지
    val bigPlayIcon: String,// 버튼 누를시 커지는 이미지
    val detailIcon: String, // 기체 상세설명 이미지
    val bulletImg: String, //총알 이미지
    ) {
    PLANE1(
        "/images/PlayerPlane1.png",
        "/images/BigPlane1.png",
        "/images/PlaneDetailImg1.png",
        "/images/bullet1.png"
    ),
    PLANE2(
        "/images/PlayerPlane2.png",
        "/images/BigPlane2.png",
        "/images/PlaneDetailImg2.png",
        "/images/bullet2.png"
    ),

    PLANE3(
        "/images/PlayerPlane3.png",
        "/images/BigPlane3.png",
        "/images/PlaneDetailImg3.png",
        "/images/bullet3.png"
    )
}