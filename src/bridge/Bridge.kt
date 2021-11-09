package bridge

import bridge.Bridge.CircleShape

//桥接模式将抽象部分与实现部分分离，使它们都可以独立地进行变化。
//什么叫做抽象与它的实现分离，这并不是说，让抽象类与其派生类分离，因为这没有任何意义。
// 实现指的是抽象类和它的派生类用来实现自己的对象。换句话说，就是指：实现系统可能有多角度分类，每一种分类都有可能变化，
// 那么就把这种多角度分离出来让它们独立变化，减少它们之间的耦合。

class Bridge {
    interface DrawingAPI {
        fun drawCircle(x: Double, y: Double, radius: Double)
    }

    class DrawingAPI1 : DrawingAPI {
        override fun drawCircle(x: Double, y: Double, radius: Double) {
            println("API1.circle at $x:$y radius $radius")
        }
    }

    class DrawingAPI2 : DrawingAPI {
        override fun drawCircle(x: Double, y: Double, radius: Double) {
            println("API2.circle at $x:$y radius $radius")
        }
    }

    abstract class Shape(protected var drawingAPI: DrawingAPI) {

        abstract fun draw()

        abstract fun resizeByPercentage(pct: Double)
    }

    class CircleShape(
        private var x: Double,
        private var y: Double,
        private var radius: Double,
        drawingAPI: DrawingAPI
    ) : Shape(drawingAPI) {

        override fun draw() {
            drawingAPI.drawCircle(x, y, radius)
        }

        override fun resizeByPercentage(pct: Double) {
            radius *= (1.0 + pct / 100.0)
        }
    }
}

fun main() {
    listOf(
        CircleShape(1.0, 1.0, 1.0, Bridge.DrawingAPI1()),
        CircleShape(5.0, 7.0, 11.0, Bridge.DrawingAPI2())
    ).forEach {
        it.resizeByPercentage(2.5)
        it.draw()
    }
}