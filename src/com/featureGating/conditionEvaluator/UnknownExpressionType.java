package com.featureGating.conditionEvaluator;

public class UnknownExpressionType extends Exception {
    public UnknownExpressionType() {
        super("Not supported expression type.");
    }
}
