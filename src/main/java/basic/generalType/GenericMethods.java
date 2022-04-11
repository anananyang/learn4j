package basic.generalType;

public class GenericMethods {
    public <T> void f(T t) {
        Class clazz = t.getClass();
        String simpleName = clazz.getSimpleName();
        System.out.println(simpleName);
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f(1);
        gm.f("1");
        gm.f('1');
        gm.f(1l);
        gm.f(1.0f);
        gm.f(1.0);
    }
}
