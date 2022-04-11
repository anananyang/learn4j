package basic.stringTest;

public class StringTest {

    public String toString() {
//        return "test str 1 " + this;
        return "test str 1 " + super.toString();
    }

    public static void main(String[] args) {
        String str1 = "test str 1";
        // 将字符串中的 1 替换成其他数字
        String str2 = str1.replace('1', '2');
        // 判断字符串的地址是否发生变化
        System.out.println("is the same string object? " + (str1 == str2));

        String str3 = "test str" + " 1";
        System.out.println("is the same string object? " + (str1 == str3));

//        String str4 = "test str 4";
//        // 将字符串中的 1 替换成其他数字
//        String str5 = "test str 5";
//        // 判断字符串的地址是否发生变化
//        System.out.println("is the same string object? " + (str1 == str2));

        StringTest stringTest = new StringTest();
        stringTest.toString();
    }

}
