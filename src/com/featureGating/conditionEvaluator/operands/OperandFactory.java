package com.featureGating.conditionEvaluator.operands;

public class OperandFactory {
    public static Operand getOperand(Object value) {
        if(value instanceof Boolean) {
            return new BooleanOperand(value);
        }

        try {
            return new NumericOperand(Double.parseDouble(value.toString()));
        } catch (NumberFormatException nfe) {
            return new StringOperand(value);
        }
    }
}
