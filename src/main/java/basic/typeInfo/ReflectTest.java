package basic.typeInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    public static void main(String[] args) {
        Class<B> bClass = B.class;

//        printFields(bClass.getDeclaredFields());
//        printMethods(bClass.getDeclaredMethods());
//        printConstructors(bClass.getDeclaredConstructors());
//
//
//        printFields(bClass.getFields());
//        printMethods(bClass.getMethods());
//        printConstructors(bClass.getConstructors());

        B b = new B();
        try {
            Field field = b.getClass().getField("TEST_STR");
            if (field.isAccessible()) {
                field.setAccessible(true);
            }
            System.out.println("TEST_STR = " + field.get(b));
            field.set(b, "just for fun");
            System.out.println("after changing, TEST_STR = " + field.get(b));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private static void printFields(Field[] fields) {
        System.out.println("-------- fields ----------");
        if (isEmpty(fields)) {
            System.out.println("the class has no fields");
            return;
        }
        for (Field field : fields) {
            System.out.println(field.toString());
        }

    }

    private static void printMethods(Method[] methods) {
        System.out.println("-------- methods ----------");
        if (isEmpty(methods)) {
            System.out.println("the class has no methods");
            return;
        }
        for (Method method : methods) {
            System.out.println(method.toString());
        }
    }

    private static void printConstructors(Constructor[] constructors) {
        System.out.println("-------- constructors ----------");
        if (isEmpty(constructors)) {
            System.out.println("the class has no constructors");
            return;
        }
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toString());
        }
    }


    private static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }
}

class A {

}

interface C {

}

class B extends A implements C {
    private static final String STATIC_TEST_STR = "just for testing";
    public final String TEST_STR = "just for testing";

    public static void eMethod() {
    }

    public void fMethod() {
    }

    void gMethod(int i) {
    }

    void hMethod(Long i) {
    }

    private void iMethod(Integer[] arr) {
    }
}