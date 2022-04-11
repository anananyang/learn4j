package basic.typeInfo;

public class LoadClassTest {

    public static void main(String[] args) {
//        try {
//            Class gumClass = Class.forName("basic.typeInfo.Gum");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("after Class.forName(\"Gum\"}");
//
//        Class cookieClass = Cookie.class;
//        try {
//            Cookie cookie = (Cookie)cookieClass.newInstance();
//        } catch (InstantiationException e) {
//
//        } catch (IllegalAccessException e) {
//
//        }
//
//
//        // 基本类型
//        Class booleanClass = boolean.class;
//        Class byteClass = byte.class;
//        Class shortClass = short.class;
//        Class charClass = char.class;
//        Class intClass = int.class;
//        Class longClass = long.class;
//        Class floatClass = float.class;
//        Class doubleClass = double.class;
//        // 数组
//        Class arrayClass = byte[].class;
//        // 接口
//        Class interfaceClass = ClassTestInterface.class;
//
//        Class BooleanClass = Boolean.TYPE;
//        Class ByteClass = Byte.TYPE;
//        Class ShortClass = Short.TYPE;
//        Class CharClass = Character.TYPE;
//        Class IntClass = Integer.TYPE;
//        Class LongClass = Long.TYPE;
//        Class FloatClass = Float.TYPE;
//        Class DoubleClass = Double.TYPE;
//
//        Class<? extends Number> numberClass = Integer.class;
//
//        System.out.println("does Integer.class equal Integer.TYPE?" + (int.class.equals(Integer.TYPE)));


//        ClassTestInterface classTestInterface =  new Cookie();;
//        Cookie cookie = null;
////        if(classTestInterface instanceof Cookie) {
////            cookie = (Cookie)classTestInterface;
////        }
//
////        if(Cookie.class.isInstance(classTestInterface)) {
////            cookie = (Cookie)classTestInterface;;
////        }
//
//        if((Cookie.class.isAssignableFrom(classTestInterface.getClass()))) {
//            cookie = (Cookie)classTestInterface;;
//        }
//
//        System.out.println("cookie = " + cookie);


        Class<Candy> candyClass = Candy.class;
        System.out.println("Simple class name: " + candyClass.getSimpleName());
        System.out.println("Canonical class name: " + candyClass.getCanonicalName());

    }


}

interface ClassTestInterface {

}

class Candy implements ClassTestInterface {
    static {
        System.out.println("Loading Candy");
    }
}

class Gum implements ClassTestInterface {
    static {
        System.out.println("Loading Gum");
    }
}

class Cookie implements ClassTestInterface{
    static {
        System.out.println("Loading Cookie");
    }
}