package basic.enumTest;

import java.lang.reflect.Method;
import java.util.Iterator;

public class PrintEnumMethods {
    // 定义一个枚举
    enum Shrubbery {
        GROUND, CRAWLING, HANGING
    }

    public static void main(String[] args) {
        Class<Enum> enumClass = Enum.class;
        Class<Shrubbery> clazz = Shrubbery.class;
//        printMethods(enumClass);
        printMethods(clazz);

//        printDiffMethods(enumClass.getDeclaredMethods(), clazz.getDeclaredMethods());
    }

    private static <T> void printMethods(Class<T> clazz) {
        System.out.println("----- output " + clazz.getSimpleName() + " methods ------");
        Method[] methods = clazz.getDeclaredMethods();
        if (isEmpty(methods)) {
            return;
        }
        for(Method  method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("");
    }

    private static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }
}
