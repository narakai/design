package decorator

import decorator.Decorator.Boy

//https://blog.yorek.xyz/design-pattern/decorator/

//装饰模式也称为包装模式(Wrapper Pattern)，可以动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式相比生成子类更加灵活
//
//使用场景：
//需要透明且动态地扩展类的功能时
//
//装饰模式是以对客户端透明的方式扩展对象的功能，是继承关系的一个替代方案；而代理模式则是给一个对象提供一个代理对象，
// 并有代理对象来控制原有对象的引用。装饰模式应该为所装饰的对象增强功能；代理模式对代理的对象施加控制，但不对对象本身的功能进行增强。

class Decorator {

    abstract class Person {
        abstract fun dressed()
    }

    class Boy : Person() {
        override fun dressed() {
            println("穿了内衣内裤")
        }
    }

    open class NormalCloth(private val person: Person) : Person() {
        override fun dressed() {
            person.dressed()
        }
    }

    class CheapCloth(person: Person) : NormalCloth(person) {

        private fun dressShorts() = println("穿件短裤")

        override fun dressed() {
            super.dressed()
            dressShorts()
        }
    }

    class ExpensiveCloth(person: Person) : NormalCloth(person) {

        private fun dressShirt() = println("穿件短袖")

        private fun dressLeather() = println("穿件皮衣")

        private fun dressJean() = println("穿件牛仔衣")

        override fun dressed() {
            super.dressed()
            dressShirt()
            dressLeather()
            dressJean()
        }
    }
}

fun main() {
    val person = Boy()

    val clothCheap = Decorator.CheapCloth(person)
    clothCheap.dressed()

    println()

    val clothExpensive = Decorator.ExpensiveCloth(person)
    clothExpensive.dressed()
}