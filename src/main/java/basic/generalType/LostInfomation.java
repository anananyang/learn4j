package basic.generalType;

import java.util.*;

public class LostInfomation {

    public static void main(String[] args) {
        List<AClass> aList = new ArrayList<>();
        Map<AClass, BClass> map = new HashMap<>();
        CClass<AClass> cObj = new CClass<>();
        DClass<AClass, BClass> dObj = new DClass<>();


        System.out.println(Arrays.toString(aList.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(map.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(cObj.getClass().getTypeParameters()));
        System.out.println(Arrays.toString(dObj.getClass().getTypeParameters()));

    }

}

class AClass {}
class BClass {}
class CClass<T> {}
class DClass<K, V> {}
