package com.featureGating.offers;

import com.featureGating.conditionEvaluator.ExpressionEvaluator;
import com.featureGating.conditionEvaluator.ExpressionType;
import com.featureGating.customer.User;

import java.util.Map;

public class Feature {
    String name;
    ExpressionEvaluator expEvaluator;

    public Feature(String name, ExpressionEvaluator expEvaluator) {
        this.name = name;
        this.expEvaluator = expEvaluator;
    }

    public boolean isAllowed(Map<String, Object> user) {
        return expEvaluator.evaluate(user);
    }
}
