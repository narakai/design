package mediator

//中介者模式，用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，从而使其耦合松散，
// 而且可以独立地改变它们之间的交互。
//
//中介者模式包装了一系列对象相互作用的方式，使得这些对象不必相互明显作用。从而使它们松散耦合。
// 当某些对象之间的作用发生改变时，不会立即影响其他的一些对象之间的作用。保证这些作用可以彼此独立的变化。
// 中介者模式将多对多的相互作用转化为一对多的相互作用。中介者模式将对象的行为和协作抽象化，
// 把对象在小尺度的行为上与其他对象的相互作用分开处理

class Mediator {
    //    抽象同事类角色，定义了中介者对象的接口，它只知道中介者而不知道其他的同事对象
    abstract class Colleague(protected val mediator: Mediator)

    //    抽象中介者角色，定义了同事对象到中介者对象的接口，一般以抽象类的方式实现
    abstract class Mediator {
        abstract fun changed(c: Colleague)
    }

    class CDDevice(mediator: Mediator) : Colleague(mediator) {
        private var data: String? = null

        fun read() = data!!

        fun load() {
            data = "视频数据,音频数据"

            mediator.changed(this)
        }
    }

    class CPU(mediator: Mediator) : Colleague(mediator) {
        var dataVideo: String? = null
            private set
        var dataSound: String? = null
            private set

        fun decodeData(data: String) {
            val tmp = data.split(",")

            dataVideo = tmp[0]
            dataSound = tmp[1]

            mediator.changed(this)
        }
    }

    class GraphicsCard(mediator: Mediator) : Colleague(mediator) {

        fun videoPlay(data: String) {
            println("视频: $data")
        }
    }

    class SoundCard(mediator: Mediator) : Colleague(mediator) {

        fun soundPlay(data: String) {
            println("音频: $data")
        }
    }

    class MainBoard : Mediator() {
        var cdDevice: CDDevice? = null
        var cpu: CPU? = null
        var soundCard: SoundCard? = null
        var graphicsCard: GraphicsCard? = null

        override fun changed(c: Colleague) {
            when (c) {
                cdDevice -> handleCD(c as CDDevice)
                cpu -> handleCPU(c as CPU)
            }
        }

        private fun handleCD(cdDevice: CDDevice) {
            cpu?.decodeData(cdDevice.read())
        }

        private fun handleCPU(cpu: CPU) {
            soundCard?.soundPlay(cpu.dataSound!!)
            graphicsCard?.videoPlay(cpu.dataVideo!!)
        }
    }
}

fun main() {
    val mediator = Mediator.MainBoard()

    val cd = Mediator.CDDevice(mediator)
    val cpu = Mediator.CPU(mediator)
    val vc = Mediator.GraphicsCard(mediator)
    val sc = Mediator.SoundCard(mediator)

    mediator.cdDevice = cd
    mediator.cpu = cpu
    mediator.graphicsCard = vc
    mediator.soundCard = sc

    cd.load()
}