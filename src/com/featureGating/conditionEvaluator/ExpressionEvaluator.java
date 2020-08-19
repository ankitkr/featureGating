package com.featureGating.conditionEvaluator;

import com.featureGating.conditionEvaluator.operands.Operand;
import com.featureGating.conditionEvaluator.operands.OperandFactory;
import com.featureGating.conditionEvaluator.operators.Operator;
import com.featureGating.conditionEvaluator.operators.OperatorFactory;
import com.featureGating.conditionEvaluator.operators.exceptions.OperandMismatchException;
import com.featureGating.conditionEvaluator.tokenizers.Tokenizer;

import java.util.*;

public class ExpressionEvaluator {
    private Queue<String> postfixTokens;
    private final OperatorRegistry operatorRegistry;

    public ExpressionEvaluator(Tokenizer tokenizer,
                               ExpressionType expressionType) {
        this.operatorRegistry = OperatorRegistry.getInstance();
        this.postfixTokens = new LinkedList<>();
        switch (expressionType){
            case INFIX:
                convertInfixToPostfixExpression(tokenizer);
                break;
            case POSTFIX:
                parsePostfixExpression(tokenizer);
                break;
        }
    }

    public Boolean evaluate(Map<String, Object> user) throws OperandMismatchException {
        Stack<Operand> operandStack = new Stack<>();
        List<Operand> operands;

        for(String elem: postfixTokens) {
            if(!OperatorFactory.isOperator(elem)) {
                operandStack.push(OperandFactory.getOperand(elem));
            } else {
                operands = new LinkedList<>();
                Integer operandCount = 0;
                Operator operator = OperatorFactory.getOperator(elem);
                while(operandCount < operator.getOperandCount()) {
                    operands.add(operandStack.pop());
                    operandCount++;
                }
                Boolean result = operator.apply(operands);
                operandStack.push(OperandFactory.getOperand(result));
            }
        }

        return (Boolean)operandStack.pop().getValue();
    }

    private void convertInfixToPostfixExpression(Tokenizer tokenizer) {
        Stack<String> stack = new Stack();
        String nextToken = tokenizer.getNextToken();
        while (nextToken != null) {
            if(nextToken.equals("(")) {
                stack.push(nextToken);
            } else if(nextToken.equals(")")) {
                while (!stack.empty() && !(stack.peek().equals("("))) {
                    this.postfixTokens.add(stack.pop());
                }
                stack.pop();
            } else if(OperatorFactory.isOperator(nextToken)) {
                String topElem = stack.peek();
                if(this.operatorRegistry.getOperator(nextToken).getPrecedence() >=
                        this.operatorRegistry.getOperator(topElem).getPrecedence()) {
                    stack.push(nextToken);
                } else {
                    while(!stack.empty() &&
                            this.operatorRegistry.getOperator(nextToken).getPrecedence() <
                                    this.operatorRegistry.getOperator(topElem).getPrecedence()) {
                        this.postfixTokens.add(stack.pop());
                    }
                    stack.push(nextToken);
                }
            } else {
                this.postfixTokens.add(nextToken);
            }

            nextToken = tokenizer.getNextToken();
        }
    }

    private void parsePostfixExpression(Tokenizer tokenizer) {
        String nextToken = tokenizer.getNextToken();
        while (nextToken != null) {
            this.postfixTokens.add(nextToken);
            nextToken = tokenizer.getNextToken();
        }
    }
}
