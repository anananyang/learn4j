package basic.generalType;

public class GenericBase<T> {
    private T element;
    public T get() {
        return element;
    }
    public void set(T element) {
        this.element = element;
    }

}


class Derived<T> extends GenericBase<T> {}

class Derived2 extends GenericBase {}

// 继承时使用无界通配符会报错
//class Derived3 extends GenericBase<?> {}

class DerivedMain {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Derived2 derived2 = new Derived2();
        Object object = derived2.get();
        derived2.set(object);
    }
}

class Erased<T> {
    @SuppressWarnings("unchecked")
    public void f(Object arg) {
//        if(arg instanceof T) {
//            // do something
//        }
//        T t = new T();
//        T[] array = new T[100];
        T[] array2 = (T[])new Object[100];
    }
}