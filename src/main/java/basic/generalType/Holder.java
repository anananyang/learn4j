package basic.generalType;

class Holder<T>{
    private T value;
    Holder(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

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
