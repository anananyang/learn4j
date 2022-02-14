package dfa.rg.ast;

public class RGOrNode implements RGNode{
    private RGNode firstNode;
    private RGNode secondNode;

    public RGOrNode(RGNode firstNode, RGNode secondNode) {
        this.firstNode = firstNode;
        this.secondNode = secondNode;
    }

    public String toString() {
        return firstNode.toString() + "|" + secondNode.toString();
    }
}
