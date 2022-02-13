package dfa.rg;

public class RGLexer {

    private String regexp;
    private int curPos = -1;
    private int regexpLen;

    public RGLexer(String regexp) {
        this.regexp = regexp;
        this.regexpLen = regexp.length();
    }

    public boolean hasNext() {
        return curPos < regexpLen;
    }

    public RGToken netToken() {
        curPos++;
        char c = regexp.charAt(curPos);
        RGToken token = new RGToken(curPos,
                c,
                RGOperator.optMap.containsKey(c));

        return token;
    }
}
