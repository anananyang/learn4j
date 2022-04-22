package basic.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
    public int id();

    public String description() default "no description";

    public int value() default 0;
}


class TestAnnotationTest {
    @Test(id = 1)
    void test() {
        System.out.println("TestAnnotationTest.test()");
    }
}

class TestAnnotationProcessor {

    <T> void processs(Class<T> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        if (isEmpty(methods)) {
            return;
        }
        for (Method method : methods) {
            printAnnotationSpecifition(method);
        }
    }

    private static void printAnnotationSpecifition(Method method) {
        // 新的注解
        Test annotation = method.getDeclaredAnnotation(Test.class);
        if(annotation == null) {
            return;
        }
        System.out.println("test id is " + annotation.id());
        System.out.println("test value is " + annotation.value());
        System.out.println("test description is \"" + annotation.description() + "\"");
    }

    private static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }


    public static void main(String[] args) {
        TestAnnotationProcessor processor = new TestAnnotationProcessor();
        processor.processs(TestAnnotationTest.class);
    }
}