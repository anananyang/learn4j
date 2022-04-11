package basic.generalType;

import java.util.ArrayList;
import java.util.List;

public class BoundaryType<T extends Parent> {
    private T element;

    public void set(T element) {
        System.out.println(element.getClass().getSimpleName());
        this.element = element;
    }

    public T get() {
        return element;
    }

    public void f() {
        element.f();
    }

    public static void main(String[] args) {
        BoundaryType<Parent> boundaryType = new BoundaryType<>();
        boundaryType.set(new Parent());
//        boundaryType.f();
//        System.out.println(boundaryType.get().getClass().getSimpleName());
    }
}

class Parent {
    void f() {
        System.out.println("Parent.f()");
    }
}

class Fruit {
}

class Apple extends Fruit {
}



class Orange extends Fruit {
}

class Test {
    public static void main(String[] args) {
//        ArrayList<Fruit> list = new ArrayList<Apple>();

        ArrayList<? super Apple> list = new ArrayList<Apple>();
        list.add(new Apple());
        Fruit fruit = (Fruit) list.get(0);
    }

}
