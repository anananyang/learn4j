package dfa.rg;

import java.util.HashMap;
import java.util.Map;

public abstract class RGOperator {

    public static char KLEENE = '*';
    public static char LP = '(';
    public static char RP = ')';
    public static char ALT = '|';
    public static char CONCAT = '+';

    public static Map<Character, Integer> optMap = new HashMap<Character, Integer>(){{
       put(KLEENE,  1);
       put(RP, 2);
       put(LP, 2);
       put(CONCAT, 3);
       put(ALT, 4);
    }};

    /**
     * 比较两个操作符的优先级
     * @param token1
     * @param token2
     * @return 如果 token1 中操作符的优先级大于 token2 中的操作符，则返回 true, 否则返回 false
     */
    public static boolean lessThan(RGToken token1, RGToken token2) {
        int val1 = optMap.get(token1.getChar());
        int val2 = optMap.get(token2.getChar());
        return val1 > val2;
    }

}
