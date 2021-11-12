package command

//将一个请求封装成一个对象，从而让用户使用不同的请求把客户端参数化；对请求排队或者记录请求日志，以及支持可撤销的操作
//
//使用场景
//
//需要抽象出待执行的行动，然后以参数的形式提供出来——类似于过程设计中的回调机制，而命令模式正是回调机制的一个面向对象的代替品。
//在不同的时刻指定、排列和执行请求，一个命令对象可以有与初始请求无关的生存期
//需要支持取消操作
//支持修改日志功能，这样当系统崩溃时，这些修改可以被重做一遍
//需要支持事务操作

class Command {
    /** The Command interface */
    interface Command {
        fun execute()
    }

    /** The Command for turning on the light - ConcreteCommand #1 */
    class FlipUpCommand(private val light: Light) : Command {
        override fun execute() {
            light.turnOn()
        }
    }

    /** The Command for turning off the light - ConcreteCommand #2 */
    class FlipDownCommand(private val light: Light) : Command {
        override fun execute() {
            light.turnOff()
        }
    }

    /** The Invoker class */
    class Switch {
//        private val history = mutableListOf<Command>()

        fun storeAndExecute(cmd: Command) {
//            history.add(cmd)
            cmd.execute()
        }
    }

    /** The Receiver class */
    class Light {
        fun turnOn() = println("The light is on")

        fun turnOff() = println("The light is off")
    }
}

fun main() {
    val inputArgs = listOf("ON", "OFF", "ON")

    val light = Command.Light()

    val switchUp = Command.FlipUpCommand(light)
    val switchDown = Command.FlipDownCommand(light)

    val switch = Command.Switch()

    inputArgs.forEach {
        when (it) {
            "ON" -> switch.storeAndExecute(switchUp)
            "OFF" -> switch.storeAndExecute(switchDown)
        }
    }
}