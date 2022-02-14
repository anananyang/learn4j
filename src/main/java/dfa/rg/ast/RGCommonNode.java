package dfa.rg.ast;

import dfa.rg.RGToken;

public class RGCommonNode implements RGNode{
    private RGToken token;

    public RGCommonNode(RGToken token) {
        this.token = token;
    }

    public String toString() {
        return "" + this.token.getChar();
    }
}
