package dfa.rg.ast;

public class RGParenthesesNode implements RGNode{
    private RGNode node;

    public RGParenthesesNode(RGNode node) {
        this.node = node;
    }

    public String toString() {
        return "(" + node.toString() + ")";
    }
}
