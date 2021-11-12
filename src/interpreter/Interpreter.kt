package interpreter

import java.util.*

//给定一个语言，定义它的文法的一种表示，并定义一个解释器，该解释器使用该表示来解释语言中的句子
//
//使用场景
//
//如果某个简单的语言需要解释执行而且可以将该语言中的语句表示为一个抽象语法树时可以考虑使用解释器模式
//在某些特定领域出现不断重复的问题时，可以将该领域的问题转化为一种语法规则下的语句，然后构建解释器来解释该语言

class Interpreter {
    abstract class ArithmeticExpression {

        abstract fun interpret(): Int
    }

    class NumberExpression(private val num: Int) : ArithmeticExpression() {

        override fun interpret() = num
    }

    abstract class OperatorExpression(
        protected val exp1: ArithmeticExpression,
        protected val exp2: ArithmeticExpression
    ) : ArithmeticExpression()

    class AdditionExpression(
        exp1: ArithmeticExpression,
        exp2: ArithmeticExpression
    ) : OperatorExpression(exp1, exp2) {

        override fun interpret() = exp1.interpret() + exp2.interpret()
    }

    class Calculator(expression: String) {
        private val mExpStack = Stack<ArithmeticExpression>()

        init {
            var exp1: ArithmeticExpression
            var exp2: ArithmeticExpression

            val elements = expression.split(" ")

            var i = 0
            while (i < elements.size) {
                when (elements[i][0]) {
                    '+' -> {
                        exp1 = mExpStack.pop()
                        exp2 = NumberExpression(Integer.valueOf(elements[++i]))
                        mExpStack.push(AdditionExpression(exp1, exp2))
                    }

                    else -> mExpStack.push(NumberExpression(Integer.valueOf(elements[i])))
                }
                i++
            }
        }

        fun calculate() = mExpStack.pop().interpret()
    }
}

fun main() {
    val calc = Interpreter.Calculator("153 + 3589 + 118 + 555")
    println(calc.calculate())
}