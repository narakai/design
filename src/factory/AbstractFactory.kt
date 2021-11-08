package factory

//为创建一组相关或者是相互依赖的对象提供一个接口，而不需要指定它们的具体类。

class AbstractFactory {
    interface ITire {
        fun tire()
    }

    interface IEngine {
        fun engine()
    }

    interface IBrake {
        fun brake()
    }

    abstract class CarFactory {
        abstract fun createTire(): ITire

        abstract fun createEngine(): IEngine

        abstract fun createBrake(): IBrake
    }

    class NormalTire : ITire {
        override fun tire() {
            println("normal tire")
        }
    }

    class SUVTire : ITire {
        override fun tire() {
            println("SUV tire")
        }
    }

    class DomesticEngine : IEngine {
        override fun engine() {
            println("domestic engine")
        }
    }

    class ImportEngine : IEngine {
        override fun engine() {
            println("import engine")
        }
    }

    class NormalBrake : IBrake {
        override fun brake() {
            println("normal brake")
        }
    }

    class SeniorBrake : IBrake {
        override fun brake() {
            println("senior brake")
        }
    }

    class AudiFactory : CarFactory() {
        override fun createTire(): ITire = SUVTire()

        override fun createEngine(): IEngine = ImportEngine()

        override fun createBrake(): IBrake = SeniorBrake()
    }

    class QQFactory : CarFactory() {
        override fun createTire(): ITire = NormalTire()

        override fun createEngine(): IEngine = DomesticEngine()

        override fun createBrake(): IBrake = NormalBrake()
    }
}

fun main() {
    val audiFactory = AbstractFactory.AudiFactory()
    audiFactory.createTire().tire()
    audiFactory.createEngine().engine()
    audiFactory.createBrake().brake()

    println()

    val qqFactory = AbstractFactory.QQFactory()
    qqFactory.createTire().tire()
    qqFactory.createEngine().engine()
    qqFactory.createBrake().brake()
}