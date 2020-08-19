package com.featureGating.conditionEvaluator.operators;

public class OperatorFactory {
    public static Operator getOperator(String symbol) {
        switch(symbol) {
            case GtEqOperator.SYMBOL:
                return new GtEqOperator(9);
            case GtOperator.SYMBOL:
                return new GtOperator(9);
            case LtEqOperator.SYMBOL:
                return new LtEqOperator(9);
            case LtOperator.SYMBOL:
                return new LtOperator(9);
            case EqualsOperator.SYMBOL:
                return new EqualsOperator(8);
            case NotEqualsOperator.SYMBOL:
                return new NotEqualsOperator(8);
            case AndOperator.SYMBOL:
                return new AndOperator(4);
            case OrOperator.SYMBOL:
                return new OrOperator(3);
            default:
                return null;
        }
    }

    public static Boolean isOperator(String symbol) {
        switch (symbol) {
            case GtEqOperator.SYMBOL:
            case GtOperator.SYMBOL:
            case LtEqOperator.SYMBOL:
            case LtOperator.SYMBOL:
            case EqualsOperator.SYMBOL:
            case NotEqualsOperator.SYMBOL:
            case AndOperator.SYMBOL:
            case OrOperator.SYMBOL:
                return true;
            default:
                return false;
        }
    }
}
