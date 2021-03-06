package singleton;

//定义以及使用场景
//
//        确保某一个类只有一个实例，而且向整个系统提供这个实例
//        确保某个类有且仅有一个对象的场景，避免产生多个对象消耗过多的资源；或者某种类型的对象应该有且只有一个。

public class Singleton {
    //1. 饥汉模式
//    private Singleton() {
//    }
//
//    private static final Singleton INSTANCE = new Singleton();
//
//    public static Singleton getInstance() {
//        return INSTANCE;
//    }

    //2. 懒汉模式
//    private Singleton() {
//
//    }
//
//    private static Singleton mInstance;
//
//    public static synchronized Singleton getInstance() {
//        if (mInstance == null) {
//            mInstance = new Singleton();
//        }
//        return mInstance;
//    }


//    优点
//
//    只有需要时才会初始化，在一定程度上节约了资源
//            缺点
//
//    第一次加载时需要及时进行初始化，反应稍慢
//    且每次调用都会进行同步，造成不必要的同步开销。一般不建议使用

    //3. Double Check Lock(DCL)模式
//    private Singleton() {
//
//    }
//
//    private volatile static Singleton mInstance;
//
//    public static Singleton getInstance() {
//        if (mInstance == null) {
//            synchronized (Singleton.class) {
//                if (mInstance == null) {
//                    mInstance = new Singleton();
//                }
//            }
//        }
//        return mInstance;
//    }

//    优点
//
//    DCL的优点就是资源利用率高，只有第一次执行getInstance才会初始化。
//    缺点
//
//    第一次加载时反应稍慢，也由于Java内存模型的原因会偶尔导致失效。但是将sInstance的定义加上volatile就能保证程序的正确性。
//    sInstance = new Singleton()这句代码会编译成为多条汇编指令，大致有三件事
//
//            1. 给Singleton分配内存
//            2. 调用Singleton()的构造方法，初始化成员字段
//            3. 将sInstance对象指向分配的内存空间

//    由于Java编译器允许处理器乱序执行，以及JDK1.5之前的JMM(Java Memory Model)中Cache、寄存器到主内存回写循序的规定，
//    上面第二条、第三条的顺序时无法保证的。也就是说，执行顺序可能是1-2-3，也可能是1-3-2。
//    如果是后者，而且在3执行完毕、2未执行之前，被切换到线程B上，这时sInstance已经在线程A上执行过第三点了，
//    sInstance已经非空，所以B直接取走了sInstance，再使用时就会出错。这就是 DCL失效问题。
//    在JDK1.5以后，可以增加volatile关键词，可以保证sInstance对象每次都从主内存中读取。

    //4. 静态内部类模式
//    private Singleton() {
//
//    }
//
//    public static Singleton getInstance() {
//        return SingletonHolder.SINGLETON;
//    }
//
//    private static class SingletonHolder {
//        private static final Singleton SINGLETON = new Singleton();
//    }

//    当第一次加载Singleton类时不会初始化sInstance，只有在第一次调用Singleton#getInstance方法时才会导致sInstance被初始化。
//    因此，第一次调用getInstance方法会导致虚拟机加载SingletonHolder类，这种方式不仅能够保证线程安全，
//    也能够保证单例对象的唯一性，同时也延迟了单例的实例化，这是推荐的单例实现方式。

    //5. 枚举单例
//    public enum Singleton {
//        INSTANCE;
//
//        public void doSomething() {
//            // do something
//        }
//    }
//    枚举单例写法简单，而且枚举实例的创建是线程安全的，而且在任何情况下都是它一个单例。

    //    6. 使用容器实现单例模式
//    public class SingletonManager {
//        private static Map<String, Object> sObjectMap = new HashMap<>();
//
//        private SingletonManager() {
//        }
//
//        public static void registerService(String key, Object instance) {
//            if (!sObjectMap.containsKey(key)) {
//                sObjectMap.put(key, instance);
//            }
//        }
//
//        public static Object getService(String key) {
//            return sObjectMap.get(key);
//        }
//    }

//    在程序的初始，将多种单例类注入到一个统一的管理类中，在使用时根据key获取对象对应类型的对象。
//    这种方式使我们可以管理多种类型的单例，而且在使用时可以通过统一的接口进行获取操作，降低了用户的使用成本，也对用户隐藏了具体实现，降低了耦合度。


}
