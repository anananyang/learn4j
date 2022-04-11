package basic.polymorphic;

public class Main {
    public static void main(String[] args) {
//        Parent parent =  new Parent();
//        parent.f1();
//        Son son = (Son)parent;
//        son.f1();

        // 协变返回类型
//        Parent parent1 =  new Parent();
//        Parent parent2 = parent1.getObj();
//        Son son1 = new Son();
//        Son son2 = (Son)son1.getObj();

        // 构造器中的多态方法的行为
//        new Son();

        // 多态的缺陷 2。域和静态方法
//        Parent obj = new Son();
//        System.out.println("parent num = " + obj.num + "    parent.getNum() = " + obj.getNum());
//        Son son = new Son();
//        System.out.println("son num = " + son.num + "     son.getNum() = " + son.getNum());

//        Parent obj = new Son();
//        obj.staticMethod();
//        obj.dynamicMethod();

        // 多态的缺陷，覆盖私有方法
        Parent obj = new Son();
//        obj.priv();
    }
}


class Parent {

    public int num = 12;

    public Parent() {
//        System.out.println("before call Parent.f1()");
//        f1();
//        System.out.println("after call Parent.f1()");
    }

    private void priv() {
        System.out.println("Parent.priv()");
    }

    public static void staticMethod() {
        System.out.println("Parent.staticMethod()");
    }

    public void dynamicMethod() {
        System.out.println("Parent.dynamicMethod()");
    }

    public int getNum() {
        return num;
    }

    public void f1() {
        System.out.println("Parent.f1()");
    }

    public Parent getObj() {
        System.out.println("Parent.getObj()");
        return new Parent();
    }

    public static void main(String[] args) {
        Parent obj = new Son();
        obj.priv();
    }
}

class Son extends Parent {
    private int i = 6;
    public int num = 24;

    public int getNum() {
        return num;
    }

    public void priv() {
        System.out.println("Son.priv()");
    }

    public Son() {
//        System.out.println("before call Son.f1()");
//        f1();
//        System.out.println("after call Son.f1()");
    }

    public static void staticMethod() {
        System.out.println("Son.staticMethod()");
    }

    public void dynamicMethod() {
        System.out.println("Son.dynamicMethod()");
    }

    @Override
    public void f1() {
        System.out.println("Son.f1(), i = " + i);
    }

    // 协变返回类型
    @Override
    public Parent getObj() {
        System.out.println("Son.getObj()");
        return new Son();
    }
}