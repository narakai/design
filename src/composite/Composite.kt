package composite

//将对象组合成树形结构以表示"部分-整体"的层次结构，使得用户对单个对象和组合对象的使用具有一致性
//
//使用场景：
//
//表示对象的部分-整体层次结构时
//从一个整体中能够独立出部分模块或功能的场景

class Composite {
    abstract class Dir(var name: String) {
        protected val dirs = ArrayList<Dir>()

        abstract fun addDir(dir: Dir)

        abstract fun rmDir(dir: Dir)

        abstract fun clear()

        abstract fun print()

        abstract fun getFiles(): List<Dir>
    }

    class Folder(name: String) : Dir(name) {
        override fun addDir(dir: Dir) {
            dirs.add(dir)
        }

        override fun rmDir(dir: Dir) {
            dirs.remove(dir)
        }

        override fun clear() {
            dirs.clear()
        }

        override fun print() {
            print("$name(")
            val iterator = dirs.iterator()

            while (iterator.hasNext()) {
                val dir = iterator.next()
                dir.print()
                if (iterator.hasNext()) {
                    print(", ")
                }
            }
            print(")")
        }

        override fun getFiles(): List<Dir> = dirs
    }

    class File(name: String) : Dir(name) {
        override fun addDir(dir: Dir) {
            throw UnsupportedOperationException("File don't support this operation")
        }

        override fun rmDir(dir: Dir) {
            throw UnsupportedOperationException("File don't support this operation")
        }

        override fun clear() {
            throw UnsupportedOperationException("File don't support this operation")
        }

        override fun print() = print(name)

        override fun getFiles(): List<Dir> {
            throw UnsupportedOperationException("File don't support this operation")
        }
    }
}

fun main() {
    val diskC = Composite.Folder("C")
    diskC.addDir(Composite.File("log.txt"))

    val dirWin = Composite.Folder("Windows")
    dirWin.addDir(Composite.File("explorer.exe"))
    diskC.addDir(dirWin)

    val dirPer = Composite.Folder("PerfLogs")
    dirPer.addDir(Composite.File("null.txt"))
    diskC.addDir(dirPer)

    val dirPro = Composite.Folder("Program File")
    dirPro.addDir(Composite.File("ftp.txt"))
    diskC.addDir(dirPro)

    diskC.print()
}