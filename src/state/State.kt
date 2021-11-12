package state

//当一个对象内在状态改变时允许改变其行为，这个对象看起来就像是改变了其类
//
//使用场景
//
//一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为
//代码中包含大量与对象状态有关的条件语句。例如，一个操作中含有庞大的多分支语句，且这些分支依赖于该对象的状态
//状态模式将每一个条件分支放入一个独立的类中，这使得可以根据对象自身的情况将对象的状态作为一个对象，这个对象可以不依赖与其他对象而独立变化，这样通过多态来去除过多的、重复的if-else等分支语句。

class State {
    interface TvState {
        fun nextChannel()

        fun prevChannel()

        fun turnUp()

        fun turnDown()
    }

    class PowerOffState : TvState {
        override fun nextChannel() {}

        override fun prevChannel() {}

        override fun turnUp() {}

        override fun turnDown() {}
    }

    class PowerOnState : TvState {
        override fun nextChannel() {
            println("下一频道")
        }

        override fun prevChannel() {
            println("上一频道")
        }

        override fun turnUp() {
            println("调高音量")
        }

        override fun turnDown() {
            println("调低音量")
        }
    }

    interface PowerController {
        fun powerOn()

        fun powerOff()
    }

    class TvController : PowerController {
        private var state: TvState? = null

        override fun powerOn() {
            state = PowerOnState()
            println("开机了----")
        }

        override fun powerOff() {
            state = PowerOffState()
            println("关机了----")
        }

        fun nextChannel() {
            state?.nextChannel()
        }

        fun prevChannel() {
            state?.prevChannel()
        }

        fun turnUp() {
            state?.turnUp()
        }

        fun turnDown() {
            state?.turnDown()
        }
    }
}

fun main() {
    with(State.TvController()) {
        powerOn()

        nextChannel()

        turnUp()

        powerOff()

        turnDown()

        prevChannel()
    }
}