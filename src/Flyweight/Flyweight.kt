package Flyweight

import java.util.*
import java.util.concurrent.ConcurrentHashMap

//使用享元对象可有效地支持大量的细粒度的对象
//
//使用场景
//
//系统中存在大量的相似对象
//细粒度的对象都具备较接近的外部状态，而且内部状态与环境无关，也就是说对象没有特定身份。
//需要缓冲池的场景

class Flyweight {
    interface Ticket {
        fun showTicketInfo(bunk: String)
    }

    class TrainTicket(
        private val from: String,
        private val to: String
    ) : Ticket {

        private var price: Int = 0

        override fun showTicketInfo(bunk: String) {
            price = Random().nextInt(300)

            println("购买从${from}到${to}的${bunk}火车票，价格为$price")
        }
    }

    object TicketFactory {
        private val sTicketMap = ConcurrentHashMap<String, Ticket>()

        fun getTicket(from: String, to: String): Ticket {
            val key = "${from}-${to}"
            return if (sTicketMap.containsKey(key)) {
                println("使用缓存 ---- $key")
                sTicketMap[key]!!
            } else {
                println("创建对象 ---- $key")
                val ticket = TrainTicket(from, to)
                sTicketMap[key] = ticket
                ticket
            }
        }
    }
}

fun main() {
    val ticket1 = Flyweight.TicketFactory.getTicket("北京", "青岛")
    ticket1.showTicketInfo("上铺")

    val ticket2 = Flyweight.TicketFactory.getTicket("北京", "青岛")
    ticket2.showTicketInfo("下铺")

    val ticket3 = Flyweight.TicketFactory.getTicket("北京", "青岛")
    ticket3.showTicketInfo("站票")
}