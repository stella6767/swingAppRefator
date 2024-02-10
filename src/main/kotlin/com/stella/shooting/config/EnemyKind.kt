package com.stella.shooting.config

import com.stella.shooting.model.EnemyUnit

enum class EnemyKind(
    val icon: String, // 플레이어 기체 이미지
    val life: Int,
    val moveFunc: (enemy: EnemyUnit) -> Unit,
    val bulletImg: String,
    val bulletInterval: Int,
    val width: Int,
    val height: Int,
) {


    Enemy1(
        "/images/enemy1.png",
        1,
        { enemy: EnemyUnit ->
            enemy.y++
        },
        "",
        0,
        50,
        50
    ),

    Enemy2(
        "/images/enemy2.png",
        20,
        { enemy: EnemyUnit ->

            if (enemy.x < 100) {
                if (enemy.y > 100) {
                    enemy.y-- // 속도는 여기서 조절하면 됨
                    enemy.x++
                }
            } else {
                enemy.y--
                if (enemy.y > 100) {
                    enemy.y--
                    enemy.x--
                }
            }
        },
        "/images/bullet2.png",
        100,
        150,
        150
    ),

    Enemy3(
        "/images/enemy3.png",
        5,
        { enemy: EnemyUnit ->
            enemy.y++
            if (enemy.y < 400) {
                enemy.x--
            }
        },
        "/images/bullet1.png",
        100,
        100,
        100
    ),

    Enemy4(
        "/images/enemy4.png",
        5,
        { enemy: EnemyUnit ->
            enemy.y++
            if (enemy.y < 400) {
                enemy.x++
            }
        },
        "/images/bullet1.png",
        100,
        100,
        100
    ),

    Enemy5(
        "/images/enemy5.png",
        10,
        { enemy: EnemyUnit ->
            enemy.y++
            if (enemy.y < 450) {
                enemy.x--
            } else {
                enemy.x++
            }
        },
        "/images/bullet2.png",
        20,
        150,
        150
    ),

    Enemy6(
        "/images/enemy6_2.png",
        30,
        { enemy: EnemyUnit ->

            if (enemy.y > 50) {
                enemy.y --
                enemy.x --

            }
        },
        "/images/missle.png",
        20,
        250,
        250
    ),

}