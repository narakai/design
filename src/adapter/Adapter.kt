package adapter

//适配器模式把一个类的接口变换成客户端所期待的另一个接口，从而使原本因接口不匹配而无法工作的两个类能够在一起工作
//
//使用场景： - 系统需要使用现有的类，而此类的接口不符合系统的需要，即接口不兼容 - 想要建立一个可以重复使用的类，
// 用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作 - 需要一个统一的输出接口，而输入端的类型不可预知

class Adapter {
    interface IVolt5 {
        fun getVolt5(): Int
    }

    class Volt220 {
        fun getVolt220() = 220
    }

    class VoltAdapter(private val volt220: Volt220) : IVolt5 {
        override fun getVolt5(): Int {
            return volt220.getVolt220() / 44
        }

    }
}

fun main() {
    val adapter = Adapter.VoltAdapter(Adapter.Volt220())
    println("输出电压：${adapter.getVolt5()}")
}