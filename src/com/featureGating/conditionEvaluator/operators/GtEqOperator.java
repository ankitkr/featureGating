package com.featureGating.conditionEvaluator.operators;

import com.featureGating.conditionEvaluator.operands.Operand;
import com.featureGating.conditionEvaluator.operators.exceptions.OperandMismatchException;

import java.util.List;

public class GtEqOperator extends Operator{
    private final Integer PRECEDENCE;
    public static final String SYMBOL = ">=";
    private static final String name = "GREATER_THAN_EQUAL_TO";
    private static final Integer operandCount = 2;;

    GtEqOperator(Integer precedence) {
        PRECEDENCE = precedence;
    }

    @Override
    public String getName() {
        return GtEqOperator.name;
    }

    @Override
    public String getSymbol() {
        return GtEqOperator.SYMBOL;
    }

    @Override
    public Integer getPrecedence() {
        return this.PRECEDENCE;
    }

    @Override
    public Integer getOperandCount() {
        return GtEqOperator.operandCount;
    }

    @Override
    public boolean apply(List<Operand> operands) throws OperandMismatchException {
        if(!validOperands(operands))
            throw new OperandMismatchException("Input Mismatch Error!!");

        int comparison = ((Comparable)operands.get(0).getValue()).compareTo(
                operands.get(1).getValue());
        return comparison >= 0;
    }
}