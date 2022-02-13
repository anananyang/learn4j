package dfa.rg;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer;
import dfa.rg.ast.RGCommonNode;
import dfa.rg.ast.RGNode;
import jdk.nashorn.internal.parser.Token;

import java.util.Stack;

public class RGParser {
    // 操作符栈
    private Stack<RGNode> optStack = new Stack<>();
    // 操操作数栈
    private Stack<RGNode> oprandStack = new Stack<>();
    // 正则表达式词法分析器
    private RGLexer rgLexer;

    public RGParser(RGLexer rgLexer) {
        this.rgLexer = rgLexer;
    }

    /**
     * 解析正则表达式
     */
    public RGNode parse() {
        /**
         * 标记位，用于判断是否要添加连接操作符。标记设置规则如下所示
         * 1. 如果当前是操作符，则将该标记位设置为 false;
         * 2. 如果当前是操作数，先将操作数入操作数栈，并做如下判断
         *    2.1、如果 addJoinOPT = true, 则添加一个连接操作符到操作符栈中
         *    2.2、如果 addJoinOPT = false, 则将 addJoinOPT 设置为 true
         */
        boolean addJoinOPT = false;

        while (rgLexer.hasNext()) {
            RGToken rgToken = rgLexer.netToken();
            if (rgToken.isOperator()) {
                pushOperator(rgToken);
            } else {
                pushOprand(addJoinOPT, rgToken);
            }
            addJoinOPT = !rgToken.isOperator();
        }

        // 正常情况下，操作数栈中应该只剩一个节点
        if (oprandStack.size() != 1) {
            throw new RuntimeException("regExpr is invalid, the oprand stack has more than one node at finish time");
        }
        return oprandStack.pop();
    }

    private void pushOperator(RGToken rgToken) {
        // TODO 处理操作符

    }

    private void pushOprand(boolean addJoinOPT, RGToken rgToken) {
        RGNode rgNode = new RGCommonNode(rgToken);
        oprandStack.push(rgNode);
        if (addJoinOPT) {
            RGToken joinToken = new RGToken(rgToken.getPos(), RGOperator.JOIN, true);
            pushOperator(joinToken);
        }
    }


    public static void main(String[] args) {
        String regexp = "(a|b)*round";
        RGLexer rgLexer = new RGLexer(regexp);
        RGParser rgParser = new RGParser(rgLexer);
        RGNode rgNode = rgParser.parse();
    }
}
