package basic.reuseClass;

public class ReuseClassTest {
    // 指定初始化
    private String str1 = "str1";
    // 构造器初始化
    private String str2;
    // 延迟初始化，只有使用时才进行初始化
    private String str3;

    public ReuseClassTest() {
        str2 = "str2";
    }

    public String getStr3() {
        if(str3 == null) {
            str3 = "str3";
        }
        return str3;
    }
}

class ParentClass {

    public void f1() {
        System.out.println("ParentClass.f1()");
    }

    protected void f2(int index) {
        System.out.println("ParentClass.f2()" + index);
    }

    private void f3() {
        System.out.println("ParentClass.f3()");
    }
}

class SubClass extends ParentClass {
    @Override
    public void f1() {
        System.out.println("SubClass.f1()");
    }
    @Override
    public void f2(int index) {
        System.out.println("SubClass.f2() " + index);
        super.f2(1);
    }

    public static void main(String[] args) {
        ParentClass obj = new ParentClass();
        obj = new SubClass();
        obj.f1();
        obj.f2(1);
    }
}