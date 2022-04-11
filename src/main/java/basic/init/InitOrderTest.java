package basic.init;

public class InitOrderTest {

    public static void main(String[] args) {
        System.out.println("------- table1 ---------");
        Table table1 = new Table();
        System.out.println("------- table2 ---------");
        Table table2 = new Table();
    }

}

// 静态代码指定初始化
class Table {

    // 静态成员初始化
    static Bowl bowl = new Bowl("static: bowl 1");
    static Bowl bow2;
    static Bowl bow3 = new Bowl("static: bowl 3");
    static {
        bow2 = new Bowl("static block: bowl 2");
    }

    static Bowl bow4 = new Bowl("static: bowl 4");
    Bowl bowl5 = new Bowl("common: bowl 5");
    Bowl bowl6 = new Bowl("common: bowl 6");
    Bowl bow7;

    {
        bow7 = new Bowl("common block: bowl 7");
    }
    Bowl bowl8 = new Bowl("common: bowl 8");
    Bowl bowl9;
    // 构造器初始化
    Table() {
        bowl9 = new Bowl("constructor common: bowl 9");
    }
}

class Bowl {
    // 构造器初始化
    Bowl(String marker) {
        System.out.println("Bowl (" + marker + ")");
    }
}