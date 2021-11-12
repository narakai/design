package visitor

import java.util.*

//封装一些作用与某种数据结构中各元素的操作，它可以在不改变这个数据结构的前提下定义作用于这些元素的新的操作。
//
//使用场景
//
//对象结构比较稳定，但经常需要在此对象结构上定义新的操作
//需要对一个对象结构中的对象进行很多不同的并且不想管的操作，而需要避免这些操作“污染”这些对象的类，也不希望在增加新操作的时候修改这些类。

class Visitor {
    abstract class Staff(var name: String) {
        val kpi = Random().nextInt(10)

        abstract fun accept(visitor: Visitor)
    }

    class Engineer(name: String) : Staff(name) {

        override fun accept(visitor: Visitor) {
            //visitor
            visitor.visit(this)
        }

        fun getCodeLines() = Random().nextInt(10 * 10000)
    }

    class Manager(name: String) : Staff(name) {
        val products = Random().nextInt(10)

        override fun accept(visitor: Visitor) {
            visitor.visit(this)
        }
    }

    interface Visitor {
        fun visit(engineer: Engineer)

        fun visit(manager: Manager)
    }

    class CEOVisitor : Visitor {
        override fun visit(engineer: Engineer) {
            println("工程师 : ${engineer.name}, KPI : ${engineer.kpi}")
        }

        override fun visit(manager: Manager) {
            println("经理 : ${manager.name}, KPI : ${manager.kpi}, 新产品数量 : ${manager.products}")
        }
    }

    class CTOVisitor : Visitor {
        override fun visit(engineer: Engineer) {
            println("工程师 : ${engineer.name}, 代码函数 : ${engineer.getCodeLines()}")
        }

        override fun visit(manager: Manager) {
            println("经理 : ${manager.name}, 产品数量 : ${manager.products}")
        }
    }

    class BusinessReport {
        private val staffs = listOf(
            Manager("王经理"),
            Engineer("工程师—Shawn.Xiong"),
            Engineer("工程师—Kael"),
            Engineer("工程师—Chaossss"),
            Engineer("工程师—Tiiime")
        )

        fun showReport(visitor: Visitor) {
            staffs.forEach {
                it.accept(visitor)
            }
        }
    }
}

fun main() {
    val report = Visitor.BusinessReport()

    println(" ------------ CEO -------------")
    report.showReport(Visitor.CEOVisitor())

    println(" ------------ CTO -------------")
    report.showReport(Visitor.CTOVisitor())
}