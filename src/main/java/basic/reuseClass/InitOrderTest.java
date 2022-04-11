package basic.reuseClass;

public class InitOrderTest {
    // 静态变量初始化
    private static String marker1 = printInit("InitOrderTest static marker 1");
    private static String marker2;
    static {
        marker2 = printInit("InitOrderTest static marker 2");
        marker4 = printInit("InitOrderTest static marker 4");
    }
    private static String marker3 = printInit("InitOrderTest static marker 3");
    private static String marker4;

    // 普通变量指定初始化
    private String marker5 = printInit("InitOrderTest marker 5");
    private String marker6;
    {
        marker6 = printInit("InitOrderTest marker 6");
        marker8 = printInit("InitOrderTest marker 8");
    }
    private String marker7 = printInit("InitOrderTest marker 7");
    private String marker8;

    // 构造器初始化
    private String marker9;

    public InitOrderTest(int index) {
        marker9 = printInit("InitOrderTest constructor marker 9");
    }


    public static String printInit(String marker) {
        System.out.println(marker);
        return marker;
    }
}

class SubClassTest extends InitOrderTest {

    // 静态变量初始化
    private static String marker11 = printInit("SubClassTest static marker 11");
    private static String marker12;
    static {
        marker12 = printInit("SubClassTest static marker 12");
        marker14 = printInit("SubClassTest static marker 14");
    }
    private static String marker13 = printInit("SubClassTest static marker 13");
    private static String marker14;

    // 普通变量指定初始化
    private String marker15 = printInit("SubClassTest marker 15");
    private String marker16;
    {
        marker16 = printInit("SubClassTest marker 16");
        marker18 = printInit("SubClassTest marker 18");
    }
    private String marker17 = printInit("SubClassTest marker 17");
    private String marker18;

    // 构造器初始化
    private String marker19;

    public SubClassTest() {
        super(9);
        marker19 = printInit("SubClassTest constuctor marker 19");
    }
}

class TestMain {
    public static void main(String[] args) {
        SubClassTest subClassTest = new SubClassTest();
    }
}