package dfa.rg;

import java.util.HashMap;
import java.util.Map;

public interface RGOperator {

    char KLEENE = '*';
    char LP = '(';
    char RP = ')';
    char OR = '|';
    char JOIN = '+';

    Map<Character, Integer> optMap = new HashMap<Character, Integer>(){{
       put(KLEENE, 1);
       put(LP, 2);
       put(RP, 2);
       put(OR, 3);
       put(JOIN, 4);
    }};

}
