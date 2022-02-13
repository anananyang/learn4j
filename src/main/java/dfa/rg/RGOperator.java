package dfa.rg;

import java.util.HashMap;
import java.util.Map;

public interface RGOperator {

    String KLEENE = "*";
    String LP = "(";
    String RP = ")";
    String OR = "|";
    String JOIN = "+";

    Map<String, Integer> optMap = new HashMap<String, Integer>(){{
       put(KLEENE, 1);
       put(LP, 2);
       put(RP, 2);
       put(OR, 3);
       put(JOIN, 4);
    }};

}
