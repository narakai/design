package facade

//要求一个子系统的外部与其内部的通信必须通过一个统一的对象进行。Facade模式提供一个高层次的接口，使得子系统更易于使用。
//
//使用场景
//
//为一个复杂子系统提供一个简单接口。子系统往往因为不断演化而变得越来越复杂，甚至可能被替换。大多数模式使用时都会产生更多、
// 更小的类，在这使子系统更具可重用性的同时也更容易对子系统进行定制、修改，这种易变性使得隐藏子系统的具体实现变得尤为重要。
// Facade可以提供一个简单统一的接口，对外隐藏子系统的具体实现、隔离变化。

//当你需要构建一个层次结构的子系统时，使用Facade模式定义子系统中每层的入口点。如果子系统之间是相互依赖的，
// 你可以让它们仅通过Facade接口进行通信，从而简化了它们之间的依赖关系。

class Facade {
    interface Camera {
        fun open()

        fun takePicture()

        fun close()
    }

    class SamsungCamera : Camera {
        override fun open() {
            println("open camera")
        }

        override fun takePicture() {
            println("takePicture")
        }

        override fun close() {
            println("close camera")
        }
    }

    interface Phone {
        fun dial()

        fun hangup()
    }

    class PhoneImpl : Phone {
        override fun dial() {
            println("dial")
        }

        override fun hangup() {
            println("hangup")
        }
    }

    class MobilePhone {
        private val phone = PhoneImpl()
        private val camera = SamsungCamera()

        fun dial() {
            phone.dial()
        }

        fun videoChat() {
            println("video chatting ----- ")
            camera.open()
            phone.dial()
        }

        fun hangup() {
            phone.hangup()
        }

        fun takePicture() {
            camera.open()
            camera.takePicture()
        }

        fun closeCamera() {
            camera.close()
        }
    }
}

fun main() {
    val nexus6 = Facade.MobilePhone()

    nexus6.takePicture()

    nexus6.videoChat()
}