package basic.init;

import java.util.Arrays;

public class Test {
    void f1(byte b) { System.out.println("f1(byte)"); }
    void f1(char c) { System.out.println("f1(char)"); }
    void f1(short s) { System.out.println("f1(short)"); }
    void f1(int i) { System.out.println("f1(int)"); }
    void f1(long l) { System.out.println("f1(long)"); }
    void f1(float f) { System.out.println("f1(float)"); }
    void f1(double f) { System.out.println("f1(double)"); }

    void f2(char c) { System.out.println("f2(char)"); }
    void f2(short s) { System.out.println("f2(short)"); }
    void f2(int i) { System.out.println("f2(int)"); }
    void f2(long l) { System.out.println("f2(long)"); }
    void f2(float f) { System.out.println("f2(float)"); }
    void f2(double f) { System.out.println("f2(double)"); }

    void f3(short s) { System.out.println("f3(short)"); }
    void f3(int i) { System.out.println("f3(int)"); }
    void f3(long l) { System.out.println("f3(long)"); }
    void f3(float f) { System.out.println("f3(float)"); }
    void f3(double f) { System.out.println("f3(double)"); }

    void f4(int i) { System.out.println("f4(int)"); }
    void f4(long l) { System.out.println("f4(long)"); }
    void f4(float f) { System.out.println("f4(float)"); }
    void f4(double f) { System.out.println("f4(double)"); }

    void f5(long l) { System.out.println("f5(long)"); }
    void f5(float f) { System.out.println("f5(float)"); }
    void f5(double f) { System.out.println("f5(double)"); }

    void f6(float f) { System.out.println("f6(float)"); }
    void f6(double f) { System.out.println("f6(double)"); }

    void f7(double f) { System.out.println("f7(double)"); }

    void printByte(byte b) {
        System.out.println(" ------ printByte --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printChar(char b) {
        System.out.println(" ------ printChar --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printShort(short b) {
        System.out.println(" ------ printShort --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printInt(int b) {
        System.out.println(" ------ printInt --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printLong(long b) {
        System.out.println(" ------ printLong --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printFloat(float b) {
        System.out.println(" ------ printFloat --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    void printDouble(double b) {
        System.out.println(" ------ printDouble --------");
        f1(b);
        f2(b);
        f3(b);
        f4(b);
        f5(b);
        f6(b);
        f7(b);
    }

    public static void main(String[] args) {
//        Test test = new Test();
//        test.printByte((byte)1);
//        test.printChar('a');
//        test.printShort((short)1);
//        test.printInt(1);
//        test.printLong(1l);
//        test.printFloat(1.0f);
//        test.printDouble(1.0);

        int[] i = {1, 2, 3,};
        System.out.println(Arrays.toString(i));
        int i2[] = {1, 2, 4, };
        System.out.println(Arrays.toString(i2));
        int i3[] = new int[]{1, 2, 4, };
        System.out.println(Arrays.toString(i3));
    }
}




