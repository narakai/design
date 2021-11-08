package proxy

//为其他对象提供一种代理以控制对这个对象的访问
//使用场景：
//当无法或不想直接访问某个对象或访问某个对象存在困难时可以通过一个代理对象来间接访问，为了保证客户端使用的透明性，
//委托对象与代理对象需要实现相同的接口。

class Proxy {
    interface ILawsuit {
        fun submit()
        fun burden()
        fun defend()
        fun finish()
    }

    class Alice : ILawsuit {
        override fun submit() {
            println("submit")
        }

        override fun burden() {
            println("burden")
        }

        override fun defend() {
            println("defend")
        }

        override fun finish() {
            println("finish")
        }
    }

    class Lawyer(var lawsuit: ILawsuit) : ILawsuit {
        override fun submit() {
            lawsuit.submit()
        }

        override fun burden() {
            lawsuit.burden()
        }

        override fun defend() {
            lawsuit.defend()
        }

        override fun finish() {
            lawsuit.finish()
        }
    }
}

fun main() {
    val alice = Proxy.Alice()
    val lawyer = Proxy.Lawyer(alice)
    with(lawyer) {
        submit()
        burden()
        defend()
        finish()
    }
}