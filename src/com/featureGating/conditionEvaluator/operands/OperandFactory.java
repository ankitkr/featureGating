package com.featureGating.conditionEvaluator.operands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OperandFactory {
    private static final List<String> bools = new ArrayList<String>() {
        {
            add("True");
            add("true");
            add("False");
            add("false");
        }
    };
    public static Operand getOperand(Object value) {
        if(value instanceof Boolean || bools.contains((String)value)) {
            return new BooleanOperand(value);
        }

        try {
            return new NumericOperand(Double.parseDouble(value.toString()));
        } catch (NumberFormatException nfe) {
            return new StringOperand(value);
        }
    }
}
