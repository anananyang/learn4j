package dfa.rg;

public class RGToken {
    // 当前字符
    private char ch;
    // 判断是否是操作符
    private boolean isOperator = false;

    public RGToken(char ch, boolean isOperator) {
        this.ch = ch;
        this.isOperator = isOperator;
    }

    public char getChar() {
        return ch;
    }

    boolean isOperator() {
        return isOperator;
    }
}
