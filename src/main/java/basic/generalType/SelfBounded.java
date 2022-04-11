package basic.generalType;


public class SelfBounded<T extends SelfBounded<T>> {
    private T element;

    public T get() {
        return element;
    }

    public SelfBounded<T> set(T element) {
        this.element = element;
        return this;
    }
}

class SubSelfBounded extends SelfBounded<SubSelfBounded> {
}

class A extends SelfBounded<A> {
    B b;

    public SelfBounded set(B b) {
        this.b = b;
        return b;
    }
}

class B extends SelfBounded<A> {
}

class C extends SelfBounded<C> {
}

class D {
}

class E {
    public static <T extends SelfBounded<T>> T f(T arg) {
        return arg.set(arg).get();
    }
}

class SelfBoundedMain {
    public static void main(String[] args) {
//        SubSelfBounded selfBounded = new SubSelfBounded();
//        SubSelfBounded selfBounded2 = new SubSelfBounded();
//        selfBounded.set(selfBounded2);
//        selfBounded2.set(selfBounded);

//        A a = new A();
//        a.set(new A());
//        a = a.set(new A()).get();

        A a = new A();
        a = E.f(a);
    }
}

class BaseClass {
}

class DerivedClass extends BaseClass {
}

class GenericSetter<T> {
    void set(T arg) {
        System.out.println("GenericSetter.set()");
    }
}

class DerivedSetter extends GenericSetter<BaseClass> {
    void set(DerivedClass arg) {
        System.out.println("DerivedClass.set()");
    }
}

class derivedSetterMain {
    public static void main(String[] args) {
        DerivedSetter derivedSetter = new DerivedSetter();
        derivedSetter.set(new BaseClass());
        derivedSetter.set(new DerivedClass());
    }
}


interface Performs {
    void speak();

    void sit();
}

class Dog implements Performs {
    @Override
    public void speak() {
        System.out.println("Arf!");
    }

    @Override
    public void sit() {
        System.out.println("Sitting!");
    }
}

class Robot implements Performs {
    @Override
    public void speak() {
        System.out.println("Click!");
    }

    @Override
    public void sit() {
        System.out.println("Clank!");
    }
}

class Communicate {
    public static <T extends Performs> void performm(T t) {
        t.speak();
        t.sit();
    }

    public static void main(String[] args) {
        Communicate.performm(new Dog());
        Communicate.performm(new Robot());
    }
}
