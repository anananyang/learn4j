package dfa.rg;

public class RGLexer {

    private String regexp;
    private int curPos = 0;
    private int regexpLen;

    public RGLexer(String regexp) {
        if(null == regexp ) {
            throw new RuntimeException("regexp is null");
        }
        this.regexp = regexp;
        this.regexpLen = regexp.length();
    }
    public RGToken netToken() {

        while(curPos < regexpLen) {
            Character c = regexp.charAt(curPos);
            curPos++;
            if(c != ' ') {
                return new RGToken(c, RGOperator.optMap.containsKey(c));
            }
        }
        return null;
    }
}
