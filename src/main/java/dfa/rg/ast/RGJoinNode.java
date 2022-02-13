package dfa.rg.ast;

public class RGJoinNode implements RGNode{
    private RGNode firstNode;
    private RGNode secondNode;

    public RGJoinNode(RGNode firstNode, RGNode secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }
}
