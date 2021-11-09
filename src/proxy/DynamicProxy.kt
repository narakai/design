package proxy

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class DynamicProxy {

    //IPurchaseHouse: 抽象买房接口
    interface IPurchaseHouse {
        fun inquiryPrice()//询价

        fun visitHouse()//看房

        fun payDeposit()//付定金

        fun signAgreement()//签合同

        fun payMoney()//付钱

        fun getHouse()//拿房
    }

    //HouseOwnerKt: 房子拥有者(房东)
    class HouseOwner : IPurchaseHouse {
        override fun inquiryPrice() {
            println("HouseOwner提出房子价格: 200W RMB")
        }

        override fun visitHouse() {
            println("HouseOwner同意买房者来看房子")
        }

        override fun payDeposit() {
            println("HouseOwner收了买房者1W RMB定金")
        }

        override fun signAgreement() {
            println("HouseOwner与买房者签订合同")
        }

        override fun payMoney() {
            println("买房者付钱给HouseOwner")
        }

        override fun getHouse() {
            println("买房者拿到房子")
        }
    }

    class ProxyHandler(any: Any) : InvocationHandler {
        private val mAny: Any = any

        @Throws(Throwable::class)
        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
//            return method!!.invoke(mAny, args)
//            　　在代理真实对象前我们可以添加一些自己的操作，中介收取中介费
            println("Before invoke " + method?.name)
            method?.invoke(mAny, *args.orEmpty())
            println("After invoke " + method?.name)
            return null
        }
    }
}

fun main() {
    val houseOwner = DynamicProxy.HouseOwner()
    val proxyHandler = DynamicProxy.ProxyHandler(houseOwner)
    //Proxy.newProxyInstance方法动态构造一个代理中介，需要传入被代理类的ClassLoader、共同接口集合和dynamicProxy实例对象
    val agent = Proxy.newProxyInstance(
        DynamicProxy.HouseOwner::class.java.classLoader,
        arrayOf(DynamicProxy.IPurchaseHouse::class.java),
        proxyHandler
    ) as DynamicProxy.IPurchaseHouse
    agent.inquiryPrice()
    agent.visitHouse()
    agent.payDeposit()
    agent.signAgreement()
    agent.payMoney()
    agent.getHouse()
}