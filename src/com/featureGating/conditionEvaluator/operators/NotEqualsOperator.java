package com.featureGating.conditionEvaluator.operators;

import com.featureGating.conditionEvaluator.operands.Operand;
import com.featureGating.conditionEvaluator.operators.exceptions.OperandMismatchException;

import java.util.List;

public class NotEqualsOperator extends Operator {
    private final Integer PRECEDENCE;
    public static final String SYMBOL = "!=";
    private static final String name = "NotEquals";
    private static final Integer operandCount = 2;;

    NotEqualsOperator(Integer precedence) {
        PRECEDENCE = precedence;
    }

    @Override
    public String getName() {
        return NotEqualsOperator.name;
    }

    @Override
    public String getSymbol() {
        return NotEqualsOperator.SYMBOL;
    }

    @Override
    public Integer getPrecedence() {
        return this.PRECEDENCE;
    }

    @Override
    public Integer getOperandCount() {
        return NotEqualsOperator.operandCount;
    }

    @Override
    public boolean apply(List<Operand> operands) throws OperandMismatchException {
        if(!validOperands(operands)) {
            throw new OperandMismatchException("Input Mismatch Error!!");
        }

        return (!operands.get(0).getValue().equals(operands.get(1).getValue()));
    }
}
