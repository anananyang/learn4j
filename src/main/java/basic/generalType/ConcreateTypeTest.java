package basic.generalType;

public class ConcreateTypeTest {

    public static void main(String[] args) {
        // String 类型
        Holder<String> stringHolder = new Holder<>("Just for fun");
        System.out.println("hold value is " + stringHolder.getValue());
        stringHolder.setValue("happy now");
        System.out.println("hold value is " + stringHolder.getValue());

        // Integer 类型
        Holder<Integer> integerHolder = new Holder<>(1);
        System.out.println("hold value is " + integerHolder.getValue());
        integerHolder.setValue(2);
        System.out.println("hold value is " + integerHolder.getValue());
    }
}


