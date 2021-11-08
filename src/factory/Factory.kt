@file:Suppress("UNCHECKED_CAST")

package factory

//创建一个用户创建对象的接口，让子类决定实例化哪个类。工厂方法使一个类的实例化延迟到其子类。
//
//使用场景：
//
//工厂方法模式通过依赖抽象来达到解耦的效果，并且将实例化的任务交给子类去完成，有非常好的扩展性
//在任何需要生成复杂对象的地方，都可以使用工厂方法模式。复杂对象适合使用工厂模式，用new就可以完成创建的对象无需使用工厂方法
//工厂方法模式的应用非常广泛，然而缺点也很明显，就是每次我们为工厂方法添加新的产品时，都需要编写一个新的产品类，所以要根据实际情况来权衡是否要用工厂方法模式
//如果有多个工厂的方法，那么我们称为 多工厂方法模式。
//如果我们的工厂类只有一个，那么简化掉抽象类是没有问题的，我们只需要将对应的工厂方法改为静态方法即可。这样的方式又称为 简单工厂模式 或 静态工厂模式 ，它是工厂方法模式的一个弱化版本。

abstract class Car {
    abstract fun drive()
    abstract fun navi()
}

abstract class CarFactory {
    abstract fun <T : Car> createCar(name: String): T
}

class AudiCar : Car() {
    override fun drive() {
        println("drive audi")
    }

    override fun navi() {
        println("navi audi")
    }

}

class BMWCar : Car() {
    override fun drive() {
        println("drive BMW")
    }

    override fun navi() {
        println("navi BMW")
    }

}

//根据传入name生成car
class LeonCarFactory : CarFactory() {
    override fun <T : Car> createCar(name: String): T {
        return if (name.equals("audi", true)) {
            AudiCar() as T
        } else {
            BMWCar() as T
        }
    }
}

fun main() {
    val leonFactory = LeonCarFactory()

    with(leonFactory.createCar<AudiCar>("audi")) {
        drive()
        navi()
    }
    with(leonFactory.createCar<BMWCar>("bmw")) {
        drive()
        navi()
    }
}