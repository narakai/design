package memento

//在不破环封闭的前提下，捕获一个对象的内在状态，并在该对象之外保存这个状态。这样，以后就可将该对象恢复到原先保存的状态
//
//使用场景
//
//需要保存一个对象在某个时刻的状态或部分状态
//如果用一个接口来让其他对象得到这些状态，将会暴露对象的实现细节并破坏对象的封装性，一个对象不希望外界直接访问其内部状态，通过中间对象可以间接访问其内部状态

class Memento {
    /** Memento */
    class Memento {
        var checkPoint = 0
        var lifeValue = 0
        var weapon = ""
    }

    /** Originator */
    class CallOfDuty {
        var checkPoint = 1
        var lifeValue = 100
        var weapon = "沙漠之鹰"

        fun play() {
            println("玩游戏 : 第${checkPoint}关 奋战杀敌中")

            lifeValue -= 10

            println("进度升级了")

            checkPoint++

            println("到达 第${checkPoint}关")
        }

        fun quit() {
            println(" ---------------------- ")
            println("退出前的游戏属性 : ${toString()}")
            println(" ---------------------- ")
        }

        fun createMemento(): Memento {
            return Memento().apply {
                this.checkPoint = this@CallOfDuty.checkPoint
                this.lifeValue = this@CallOfDuty.lifeValue
                this.weapon = this@CallOfDuty.weapon
            }
        }

        fun restore(memento: Memento) {
            memento.run {
                this@CallOfDuty.checkPoint = this.checkPoint
                this@CallOfDuty.lifeValue = this.lifeValue
                this@CallOfDuty.weapon = this.weapon
            }
        }

        override fun toString(): String {
            return "CallOfDuty(checkPoint=$checkPoint, lifeValue=$lifeValue, weapon='$weapon')"
        }
    }

    /** Caretaker */
    class Caretaker {
        private var memento: Memento? = null

        fun archive(memento: Memento) {
            this@Caretaker.memento = memento
        }

        fun getMemento(): Memento = memento!!
    }
}

fun main() {
    val game = Memento.CallOfDuty()
    game.play()

    val caretaker = Memento.Caretaker()
    caretaker.archive(game.createMemento())

    game.quit()

    val newGame = Memento.CallOfDuty()
    newGame.restore(caretaker.getMemento())
    newGame.play()
    newGame.quit()
}