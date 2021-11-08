package builder

//将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。
//使用场景：
//
//相同的方法，不同的执行顺序，产生不同的事件结果时
//多个部件或零件，都可以装配到一个对象中，但是产生的运行结果却又不相同时
//产品类非常复杂，或者产品类中的调用顺序不同产生了不同的结果，这个时候使用建造者模式非常合适
//当初始化一个对象特别复杂，如参数多，且很多参数都具有默认值时

data class Car @JvmOverloads constructor(
    var wheels: Int = 0,
    var color: String = ""
)

interface CarBuilder {
    fun build(): Car

    fun setColor(color: String): CarBuilder

    fun setWheels(wheels: Int): CarBuilder
}

class CarBuilderImpl : CarBuilder {
    private var car: Car = Car()

    override fun build(): Car = car

    override fun setColor(color: String): CarBuilder {
        car.color = color
        return this
    }

    override fun setWheels(wheels: Int): CarBuilder {
        car.wheels = wheels
        return this
    }
}

class CarBuilderDirector(private var builder: CarBuilder) {
    fun construct(): Car =
        with(builder) {
            setWheels(4)
            setColor("Red")
            build()
        }
}

fun main() {
    val builder = CarBuilderImpl()

    val carBuilderDirector = CarBuilderDirector(builder)

    val car = carBuilderDirector.construct()

    println(car)
}