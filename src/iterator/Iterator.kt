package iterator

//提供一种方法顺序访问一个容器对象的各个元素，而又不需要暴露该对象的内部表示
//
//使用场景
//
//遍历一个容器对象时

class Iterator {
    data class Employee(
        var name: String,
        var age: Int,
        var sex: String,
        var title: String
    )

    interface Iterator<T> {
        fun hasNext(): Boolean

        fun next(): T
    }

    class HuiIterator(
        private val array: Array<Employee>
    ) : Iterator<Employee> {
        private var position = 0

        override fun hasNext() = position <= array.size - 1

        override fun next(): Employee {
            val e = array[position]
            position++
            return e
        }
    }

    class MinIterator(
        private val list: List<Employee>
    ) : Iterator<Employee> {
        private var position = 0

        override fun hasNext() = position <= list.size - 1

        override fun next(): Employee {
            val e = list[position]
            position++
            return e
        }
    }

    interface Company<T> {
        fun iterator(): Iterator<T>
    }

    class CompanyHui : Company<Employee> {
        private val array = arrayOf(
            Employee("辉哥", 108, "男", "程序员"),
            Employee("小红", 98, "男", "程序员"),
            Employee("小辉", 88, "男", "程序员")
        )

        fun getEmployees() = array

        override fun iterator(): Iterator<Employee> = HuiIterator(array)
    }

    class CompanyMin : Company<Employee> {
        private val list = listOf(
            Employee("小民", 96, "男", "程序员"),
            Employee("小芸", 22, "女", "测试"),
            Employee("小方", 18, "女", "测试"),
            Employee("可儿", 21, "女", "设计"),
            Employee("朗晴", 19, "女", "设计")
        )

        fun getEmployees() = list

        override fun iterator(): Iterator<Employee> = MinIterator(list)
    }

}

fun main() {
    fun check(iterator: Iterator.Iterator<Iterator.Employee>) {
        while (iterator.hasNext()) {
            println(iterator.next().toString())
        }
    }

    val companyMin = Iterator.CompanyMin()
    check(companyMin.iterator())

    val companyHui = Iterator.CompanyHui()
    check(companyHui.iterator())
}