package dfa.rg;

import dfa.rg.ast.RGNode;

import java.util.Stack;

public class RGParser {

    /**
     * 解析正则表达式
     *
     * @param regExpr
     */
    public RGNode parse(String regExpr) {
        if (regExpr == null || regExpr == "") {
            throw new RuntimeException("regular express is null");
        }
        /**
         * 标记位，用于判断是否要添加连接操作符。标记设置规则如下所示
         * 1. 如果当前是操作符，则将该标记位设置为 false;
         * 2. 如果当前是操作数，先将操作数入操作数栈，并做如下判断
         *    2.1、如果 addJoinOPT = true, 则添加一个连接操作符到操作符栈中
         *    2.2、如果 addJoinOPT = false, 则将 addJoinOPT 设置为 true
         */
        boolean addJoinOPT = false;
        // 正则表达式词法分析器
        RGLexer rgLexer = new RGLexer(regExpr);
        // 操作符栈
        Stack<RGNode> optStack = new Stack<>();
        // 操操作数栈
        Stack<RGNode> oprandStack = new Stack<>();

        while (rgLexer.hasNext()) {
            RGToken rgToken = rgLexer.netToken();
            if(!rgToken.isOperator()) {

            }
        }
        // 正常情况下，操作数栈中应该只剩一个节点
        if (oprandStack.size() != 1) {
            throw new RuntimeException("regExpr is invalid, the oprand stack has more than one node at finish time");
        }
        return oprandStack.pop();
    }


    public static void main(String[] args) {
        String str = "(a|b)*round";
        RGParser rgParser = new RGParser();
        rgParser.parse(str);

    }
}
