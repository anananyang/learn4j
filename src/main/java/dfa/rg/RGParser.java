package dfa.rg;

import dfa.rg.ast.*;

import java.util.Stack;

public class RGParser {
    // 操作符栈
    private Stack<RGToken> optStack = new Stack<>();
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
        RGToken rgToken = null;
        while ((rgToken = rgLexer.netToken()) != null) {
            System.out.println("cur char is " + rgToken.getChar());
            if (rgToken.isOperator()) {
                pushOperator(rgToken);
            } else {
                pushOprand(rgToken);
            }
        }

        while (!optStack.isEmpty()) {
            popOperator();
        }

        // 正常情况下，操作数栈中应该只剩一个节点
        return oprandStack.pop();
    }

    /**
     * 处理操作符
     *
     * @param rgToken
     */
    private void pushOperator(RGToken rgToken) {
        RGNode rgNode = null;
        switch (rgToken.getChar()) {
            case '(':
                optStack.push(rgToken);
                break;
            case ')':
                while (!optStack.isEmpty() && optStack.peek().getChar() != '(') {
                    popOperator();
                }
                // 如果是操作符栈为空
                if (optStack.isEmpty()) {
                    throw new RuntimeException(") 没有匹配的 (");
                }
                optStack.pop();
                // 判断是否需要加连接操作符
                if (oprandStack.size() > 1
                        && (optStack.isEmpty() || (optStack.peek().getChar() != '|' && optStack.peek().getChar() != '('))) {
                    RGNode temp = oprandStack.pop();
                    RGToken catToken = new RGToken(RGOperator.CONCAT, true);
                    this.pushOperator(catToken);
                    oprandStack.push(temp);
                }
                rgNode = new RGParenthesesNode(oprandStack.pop());
                oprandStack.push(rgNode);
                break;
            case '+':
            case '|':
                // reduce 操作符栈中比当前操作符优先级高的操作符
                while (!optStack.isEmpty()
                        && optStack.peek().getChar() != '('
                        && !RGOperator.lessThan(optStack.peek(), rgToken)) {
                    popOperator();
                }
                optStack.push(rgToken);
                break;
            // 默认 *,优先级最高，直接入栈
            default:
                optStack.push(rgToken);
                break;
        }
    }

    private void popOperator() {
        RGToken token = optStack.pop();
        RGNode rgNode = null, first = null, second = null;
        switch (token.getChar()) {
            case '*':
                if (oprandStack.size() < 1) {
                    throw new RuntimeException("* 没有足够的操作数");
                }
                rgNode = new RGKleeneNode(oprandStack.pop());
                oprandStack.push(rgNode);
                break;
            case '|':
                if (oprandStack.size() < 2) {
                    throw new RuntimeException("| 没有足够的操作数");
                }
                second = oprandStack.pop();
                first = oprandStack.pop();
                rgNode = new RGOrNode(first, second);
                oprandStack.push(rgNode);
                break;
            case '+':
                if (oprandStack.size() < 2) {
                    throw new RuntimeException("+ 没有足够的操作数");
                }
                second = oprandStack.pop();
                first = oprandStack.pop();
                rgNode = new RGJoinNode(first, second);
                oprandStack.push(rgNode);
                break;
        }
    }

    /**
     * 将一个操作数压入栈中，如果需要压入一个连接符，则要先压入连接符，再压入操作数
     *
     * @param rgToken
     */
    private void pushOprand(RGToken rgToken) {
        RGNode rgNode = new RGCommonNode(rgToken);
        // 1. 操作数栈不为空, 操作符栈为空
        // 2. 操作数栈不为空, 操作符栈也不为空，并且栈顶的操作符不是 | 和 （ 。如果不想判断 (，可以使用嵌套的栈，每个栈表示一个作用域
        // 符合以上条件，则需添加一个 + 操作符至操作符栈中
        if (!oprandStack.isEmpty()
                && (optStack.isEmpty() || (optStack.peek().getChar() != '|' && optStack.peek().getChar() != '('))) {
            RGToken catToken = new RGToken(RGOperator.CONCAT, true);
            this.pushOperator(catToken);
        }
        oprandStack.push(rgNode);
    }


    public static void main(String[] args) {
        String regexp = "a*(a|b)*r(o|u*)nd";
        RGLexer rgLexer = new RGLexer(regexp);
        RGParser rgParser = new RGParser(rgLexer);
        RGNode rgNode = rgParser.parse();
        System.out.println("before: " + regexp);
        System.out.println(" after: " +rgNode.toString());
    }
}
