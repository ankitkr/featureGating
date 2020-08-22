package com.featureGating.conditionEvaluator.operands;

public class StringOperand extends Operand {
    public static final String NAME = "String";
    private final Object value;

    StringOperand(Object value) {
        this.value = value;
    }

    @Override
    public String getName() {
        return StringOperand.NAME;
    }

    @Override
    public Object getValue() {
        return (String)this.value;
    }
}