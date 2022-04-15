package com.jerry.voiceassistant

import com.huawei.hmf.tasks.Task
import org.junit.Test

import org.junit.Assert.*
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.locks.ReentrantLock
import java.util.concurrent.locks.ReentrantReadWriteLock
import java.util.function.Supplier
import java.util.stream.IntStream
import java.util.stream.Stream

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun streamT()
    {
       val list= listOf<Int>()
        list.map {  }.forEach {

        }
        //Thread().join()
        val lock =ReentrantLock()
        val condition=lock.newCondition()
//        condition.await()
//        condition.signalAll()

        val rwLock=ReentrantReadWriteLock()
        val rLock=rwLock.readLock()
        val wLock=rwLock.writeLock()
        val atomicInterger=AtomicInteger()
        atomicInterger.addAndGet(2)
        //ScheduledThreadPoolExecutor.
        val threadPool=ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors()*2,
            60,TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(),
            ThreadFactory {
                Thread(it,"xu thread")
            })
        val future=threadPool.submit(Callable<String> {
           "aaa"
        })
       // val task = Task
        println("fuck ${future.get()}")

        val completableFuture=CompletableFuture.supplyAsync(::fetchData,threadPool)
//        completableFuture.get()
        //正常返回
        completableFuture.thenAccept {
            println("fuck  $it" )
        }
        //错误返回
        completableFuture.exceptionally {
            println("${it.message}")
            200
        }
      val nextFuture=  completableFuture.thenApplyAsync{
            it+10
        }
        nextFuture.thenAccept {
            println("haha  $it")
        }
        //合并 future操作 CompletableFuture.allOf()
        //CompletableFuture.allOf()
        threadPool.shutdown()
    }

    private fun fetchData():Int
    {
        //return 12/0
        return 12
    }


    class threadFactory:ThreadFactory
    {
        var index=0
        override fun newThread(r: Runnable?): Thread {
          return Thread(r,"xuxu ${index++}")
        }
    }
}