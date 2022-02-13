package dfa.rg;

public class RGToken {
    // 当前操作符所在位置
    private int pos;
    // 当前字符
    private char ch;
    // 判断是否是操作符
    private boolean isOperator = false;

    public RGToken(int pos, char ch, boolean isOperator) {
        this.pos = pos;
        this.ch = ch;
        this.isOperator = isOperator;
    }

    char getChar() {
        return ch;
    }

    int getPos() {
        return pos;
    }

    boolean isOperator() {
        return isOperator;
    }
}
