package com.featureGating.conditionEvaluator;

import com.featureGating.conditionEvaluator.operators.Operator;
import com.featureGating.conditionEvaluator.operators.OperatorFactory;

import java.util.HashMap;
import java.util.Map;

public class OperatorRegistry {
    private static OperatorRegistry instance;
    private final Map<String, Operator> registry = new HashMap<String, Operator>();
    public static synchronized OperatorRegistry getInstance() {
        if (instance == null) {
            instance = new OperatorRegistry();
        }
        return instance;
    }

    public synchronized Operator getOperator(final String symbol) {
        final Operator result;
        if (isRegistered(symbol)) {
            result = registry.get(symbol);
        } else {
            result = OperatorFactory.getOperator(symbol);
            registry.put(symbol, result);
        }
        return result;
    }

    private boolean isRegistered(final String symbol) {
        return registry.containsKey(symbol);
    }

}
