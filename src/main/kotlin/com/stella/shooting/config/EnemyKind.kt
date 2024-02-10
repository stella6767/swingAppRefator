package com.stella.shooting.config

import com.stella.shooting.model.EnemyUnit

enum class EnemyKind(
    val icon: String, // 플레이어 기체 이미지
    val life: Int,
    val moveFunc: (enemy: EnemyUnit) -> Unit
) {


    Enemy1(
        "/images/enemy1.png",
        1,
        { enemy: EnemyUnit ->
            enemy.y++
        }
    ),

    Enemy2(
        "/images/enemy2.png",
        10,
        { enemy: EnemyUnit ->

            if (enemy.x < 100) {
                if (enemy.y > 100) {
                    enemy.y-- // 속도는 여기서 조절하면 됨
                    enemy.x++
                }
            } else {
                enemy.y--
                if (enemy.y > 100) {
                    enemy.y --
                    enemy.x --
                }
            }
        }
    ),

    Enemy3(
        "/images/enemy3.png",
        3,
        { enemy: EnemyUnit ->
            enemy.y ++
            if (enemy.y < 400) {
                enemy.x --
            }
        }
    ),

    Enemy4(
        "/images/enemy4.png",
        3,
        { enemy: EnemyUnit ->
            enemy.y ++
            if (enemy.y < 400) {
                enemy.x ++
            }
        }
    ),

}