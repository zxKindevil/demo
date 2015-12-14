package com.kindevil.reflect.guava.com;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.kindevil.reflect.AccessibleTest;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * ↓↓↓↓↓↓↓↓↓↓描述↓↓↓↓↓↓↓↓↓↓
 * http://ifeve.com/guava-reflection/ example
 * @author zhangxin.zhang created on 15-12-14.
 */
public class Main {
    @Test
    public void a() {
        //由于类型擦除，你不能够在运行时传递泛型类对象——你可能想强制转换它们，并假装这些对象是有泛型的，但实际上它们没有。
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
        //returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>

        //Guava提供了TypeToken, 它使用了基于反射的技巧甚至让你在运行时都能够巧妙的操作和查询泛型类型。
        //想象一下TypeToken是创建，操作，查询泛型类型（以及，隐含的类）对象的方法。
        //Guice用户特别注意：TypeToken与类Guice的TypeLiteral很相似，
        //但是有一个点特别不同：它能够支持非具体化的类型，例如T，List<T>，甚至是List<? extends Number>；
        //TypeLiteral则不能支持。TypeToken也能支持序列化并且提供了很多额外的工具方法。
    }

    @Test
    public void b(){
        /**
         背景：类型擦除与反射
         Java不能在运行时保留对象的泛型类型信息。
         如果你在运行时有一个ArrayList<String>对象，你不能够判定这个对象是有泛型类型ArrayList<String>的
         并且通过不安全的原始类型，你可以将这个对象强制转换成ArrayList<Object>。
         但是，反射允许你去检测方法和类的泛型类型。如果你实现了一个返回List的方法，并且你用反射获得了这个方法的返回类型，
         你会获得代表List<String>的ParameterizedType。
         TypeToken类使用这种变通的方法以最小的语法开销去支持泛型类型的操作。
         */

        //获取一个基本的、原始类的TypeToken非常简单：
        TypeToken<String> stringTok = TypeToken.of(String.class);
        System.out.println(stringTok);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        System.out.println(intTok);

        //为获得一个含有泛型的类型的TypeToken —— 当你知道在编译时的泛型参数类型 —— 你使用一个空的匿名内部类：
        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {};
        System.out.println(stringListTok);

        //或者你想故意指向一个通配符类型：
        TypeToken<Map<?, ?>> wildMapTok = new TypeToken<Map<?, ?>>() {};
        System.out.println(wildMapTok);

        TypeToken<Map<Integer, String>> maptok = new TypeToken<Map<Integer, String>>() {};
        System.out.println(maptok);
    }

    @Test
    public void c(){
        //TypeToken提供了一种方法来动态的解决泛型类型参数，如下所示：
        TypeToken<Map<String, BigInteger>> mapToken = mapToken(
                TypeToken.of(String.class),
                TypeToken.of(BigInteger.class)
        );
        TypeToken<Map<Integer, Queue<String>>> complexToken = mapToken(
                TypeToken.of(Integer.class),
                new TypeToken<Queue<String>>() {}
        );
    }
    static <K, V> TypeToken<Map<K, V>> mapToken(TypeToken<K> keyToken, TypeToken<V> valueToken) {
        return new TypeToken<Map<K, V>>() {}
                .where(new TypeParameter<K>() {}, keyToken)
                .where(new TypeParameter<V>() {}, valueToken);
    }

    @Test
    public void d(){
        /**
         注意如果mapToken只是返回了new TypeToken>()，它实际上不能把具体化的类型分配到K和V上面，举个例子
         */
        System.out.println(Util.<String, BigInteger>incorrectMapToken());
        // just prints out "java.util.Map<K, V>"

        //或者，你可以通过一个子类（通常是匿名）来捕获一个泛型类型并且这个子类也可以用来替换知道参数类型的上下文类。
        TypeToken<String> type = new IKnowMyType<String>() {}.type;// returns a correct TypeToken<String>
        System.out.println(type);
    }
    static class Util {
        static <K, V> TypeToken<Map<K, V>> incorrectMapToken() {
            return new TypeToken<Map<K, V>>() {};
        }
    }
    abstract class IKnowMyType<T> {
        TypeToken<T> type = new TypeToken<T>(getClass()) {};
    }

    @Test
    public void e(){
        //TypeToken支持很多种类能支持的查询，但是也会把通用的查询约束考虑在内。
        //支持的查询操作包括：

        TypeToken<String> typeToken = TypeToken.of(String.class);
        TypeToken<ArrayList<String>> stringListTok = new TypeToken<ArrayList<String>>() {};

        System.out.println(typeToken.getType()); //获得包装的java.lang.reflect.Type.
        System.out.println(stringListTok);
        System.out.println(typeToken.getRawType()); //返回大家熟知的运行时类,简单来说就是不带泛型
        System.out.println(stringListTok.getRawType());

        System.out.println(stringListTok.getTypes());

        TypeToken<ArrayList<String>>.TypeSet types = stringListTok.getTypes();
        System.out.println(types.rawTypes());
    }

    @Test
    public void f() throws NoSuchMethodException {
        /**
         TypeToken可以解析出泛型的参数具体是什么类型
         */

        TypeToken<Function<Integer, String>> funToken = new TypeToken<Function<Integer, String>>() {};
        TypeToken<?> funResultToken = funToken.resolveType(Function.class.getTypeParameters()[1]);
        System.out.println(funResultToken.getType());
        System.out.println(funToken.resolveType(Function.class.getTypeParameters()[0]));
        // returns a TypeToken<String>


        /**
         TypeToken将Java提供的TypeVariables和context token中的类型变量统一起来。
         这可以被用来一般性地推断出在一个类型相关方法的返回类型：
         */
        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {};
        TypeToken<?> entrySetToken = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
        System.out.println(entrySetToken);
        // returns a TypeToken<Set<Map.Entry<String, Integer>>>
    }

    @Test
    public void g(){
        //Guava的Invokable是对java.lang.reflect.Method和java.lang.reflect.Constructor的流式包装。
        // 它简化了常见的反射代码的使用。一些使用例子：
        //方法是否是public的?

        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {};
        //JDK:
        Method[] methods = AccessibleTest.class.getMethods();
        Modifier.isPublic(methods[0].getModifiers());
        //Guava Invokable:
        Invokable<Map<String, Integer>, Object> invokable = mapToken.method(methods[0]);
        invokable.isPublic();

        //方法是否是package private?
        //JDK:
         if(!(Modifier.isPrivate(methods[0].getModifiers()) || Modifier.isPublic(methods[0].getModifiers()))){
             System.out.println(true);
         }

        //Invokable:
        invokable.isPackagePrivate();

//        方法是否能够被子类重写？

//        JDK:
//        if(!(Modifier.isFinal((methods[0].getModifiers())
//                || Modifier.isPrivate((methods[0].getModifiers())
////                || Modifier.isStatic((methods[0].)
//                || Modifier.isFinal((methods[0].getDeclaringClass().getModifiers()) )) {
//
//        }

        //Invokable:
        invokable.isOverridable();

//        方法的第一个参数是否被定义了注解@Nullable？

//        JDK:
//
//        for (Annotation annotation : method.getParameterAnnotations[0]) {
//            if (annotation instanceof Nullable) {
//                return true;
//            }
//        }
//        return false;
//
//        Invokable:
//
//        invokable.getParameters().get(0).isAnnotationPresent(Nullable.class)
//
//        构造函数和工厂方法如何共享同样的代码？
//
//        你是否很想重复自己，因为你的反射代码需要以相同的方式工作在构造函数和工厂方法中？
//
//        Invokable提供了一个抽象的概念。下面的代码适合任何一种方法或构造函数：
//
//        invokable.isPublic();
//        invokable.getParameters();
//        invokable.invoke(object, args);
//
//        List的List.get(int)返回类型是什么？
//        Invokable提供了与众不同的类型解决方案：
//
//        Invokable<List<String>, ?> invokable = new TypeToken<List<String>>()        {}.method(getMethod);
//        invokable.getReturnType(); // String.class

    }
}
