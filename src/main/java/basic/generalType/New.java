package basic.generalType;

import java.util.*;

public class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<K, V>();
    }

    public static <T> List<T> list() {
        return new ArrayList<T>();
    }

    public static <T> Set<T> set() {
        return new HashSet<T>();
    }

    public static <T> Queue<T> queue() {
        return new LinkedList<T>();
    }
}

class NewMain {

    void f(Map<String, List<? extends Number>> map) {}

    public <T> T g(T t) {
        return t;
    }

    public void i() {
        this.g("just for fun");
    }

    public static <T> T h(T t) {
        return t;
    }

    public <T> void k(T... args) {
        for(T arg : args) {
            System.out.println(arg);
        }
    }

    public static void main(String[] args) {

        Map<String, List<? extends Number>> numberMap = New.map();
        NewMain main = new NewMain();
        main.f(numberMap);
        NewMain.h("Just for fun");
        main.k("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z".split(" "));
    }
}