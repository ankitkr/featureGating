package com.featureGating.conditionEvaluator.operators;

import com.featureGating.conditionEvaluator.operands.BooleanOperand;
import com.featureGating.conditionEvaluator.operands.Operand;
import com.featureGating.conditionEvaluator.operators.exceptions.OperandMismatchException;

import java.util.List;


public class OrOperator extends Operator{
    private final Integer PRECEDENCE;
    public static final String SYMBOL = "OR";
    private static final String name = "OR";
    private static final Integer operandCount = 2;;

    OrOperator(Integer precedence) {
        PRECEDENCE = precedence;
    }

    @Override
    public String getName() {
        return OrOperator.name;
    }

    @Override
    public String getSymbol() {
        return OrOperator.SYMBOL;
    }

    @Override
    public Integer getPrecedence() {
        return this.PRECEDENCE;
    }

    @Override
    public Integer getOperandCount() {
        return OrOperator.operandCount;
    }

    @Override
    public boolean apply(List<Operand> operands) throws OperandMismatchException {
        if(!validOperands(operands))
            throw new OperandMismatchException("Input Mismatch Error!!");

        if (!operands.get(0).getName().equals(BooleanOperand.NAME))
            throw new OperandMismatchException("Expected Boolean Input, but found different.");

        Boolean operand1 = (boolean)operands.get(0).getValue();
        Boolean operand2 = (boolean)operands.get(1).getValue();
        return operand1 || operand2;
    }
}
