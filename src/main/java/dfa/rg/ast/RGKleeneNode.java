package dfa.rg.ast;

public class RGKleeneNode implements RGNode{
    private RGNode node;

    public RGKleeneNode(RGNode node) {
        this.node = node;
    }
}
