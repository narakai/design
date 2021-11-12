package strategy

//策略模式定义了一系列的算法，并将每个算法封装起来，而且使他们还可以相互替换。策略模式让算法独立于使用它的客户而独立变化。
//
//使用场景
//
//针对同一类型的多种处理方式，仅仅是具体行为有差别
//需要安全地封装多种同一类型的操作时
//出现同一抽象类有多个子类，而又需要使用if-else或者switch-case来选择具体子类时

//在基本的策略模式中，选择所用具体实现的职责由客户端对象承担，并转给策略模式的Context对象。
//这本身并没有解除客户端需要选择判断的压力，可以将策略模式与简单工厂模式相结合，将策略的创建移到简单工厂中，
//这使得客户端与具体的策略彻底分离。

class Strategy {
    interface BillingStrategy {
        fun getActPrice(rawPrice: Double): Double
    }

    class NormalStrategy : BillingStrategy {
        override fun getActPrice(rawPrice: Double) = rawPrice
    }

    class HappyTimeStrategy : BillingStrategy {
        override fun getActPrice(rawPrice: Double) = rawPrice * 0.5
    }

    class Customer(var strategy: BillingStrategy) {
        private var drinks = mutableListOf<Double>()

        fun add(price: Double, quantity: Int) {
            drinks.add(strategy.getActPrice(price * quantity))
        }

        fun printBill() {
            println("Total due: ${drinks.sum()}")
            drinks.clear()
        }
    }
}

fun main() {
    val firstCustomer = Strategy.Customer(Strategy.NormalStrategy())

    firstCustomer.add(1.0, 1)

    firstCustomer.strategy = Strategy.HappyTimeStrategy()
    firstCustomer.add(1.0, 2)
    firstCustomer.printBill()


    val secondCustomer = Strategy.Customer(Strategy.HappyTimeStrategy())
    secondCustomer.add(0.8, 1)

    secondCustomer.strategy = Strategy.NormalStrategy()
    secondCustomer.add(1.3, 2)
    secondCustomer.add(2.5, 1)
    secondCustomer.printBill()
}