package basic.generalType.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DynamicSecurity {

    public static void main(String[] args) {
        List list = Collections.checkedList(new ArrayList<>(), Integer.class);
        try {
            list.add(Long.valueOf(2));
        }catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
}
