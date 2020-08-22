package com.featureGating.conditionEvaluator.operands;

public class NumericOperand extends Operand {
    public static final String NAME = "Numeric";
    private final Object value;

    NumericOperand(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return NumericOperand.NAME;
    }

    @Override
    public Object getValue() {
        return (Double)this.value;
    }
}
