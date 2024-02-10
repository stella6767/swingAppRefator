package com.stella.shooting

import org.junit.jupiter.api.Test
import java.util.concurrent.ConcurrentSkipListSet

class ThreadTest {


    @Test
    fun virtualThreadTest(){

        val threads = mutableListOf<Thread>()
        val observed = ConcurrentSkipListSet<String>()


        for (i in 0..100){

            val first = i == 0

            threads.add(Thread.ofPlatform().unstarted {
                Runnable {
                    if (first) observed.add(Thread.currentThread().name)
                    Thread.sleep(1000)
                }
            })
        }

        for (thread in threads) {
            thread.start()
        }

        for (thread in threads) {
            thread.join()
        }

        println(observed)



    }

}