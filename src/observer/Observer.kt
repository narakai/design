package observer

import java.util.*

//定义对象间的一种一对多的依赖关系，使得每当一个对象改变状态，则所有依赖于它的对象都会得到通知并被自动更新
//
//使用场景
//
//关联行为场景，需要注意的是，关联行为是可以拆分的，而不是“组合关系”。(也就是可以自由的注册、注销)
//事件多级出发场景
//跨系统的消息交换场景，如消息队列，事件总线的处理机制

class Observer {
    class EventSource : Observable(), Runnable {
        override fun run() {
            while (true) {
                val response = Scanner(System.`in`).next()
                setChanged()
                notifyObservers(response)
            }
        }
    }


}

fun main() {
    println("Enter Text: ")

    val eventSource = Observer.EventSource()

    eventSource.addObserver { _, arg ->
        println("Received response: $arg")
    }

    Thread(eventSource).start()
}