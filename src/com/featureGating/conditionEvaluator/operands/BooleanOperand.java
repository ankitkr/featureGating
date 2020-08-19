package com.featureGating.conditionEvaluator.operands;

public class BooleanOperand extends Operand {
    public static final String NAME = "Boolean";
    private final Object value;

    BooleanOperand(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return BooleanOperand.NAME;
    }

    @Override
    public Object getValue() {
        return (Boolean)this.value;
    }
}
