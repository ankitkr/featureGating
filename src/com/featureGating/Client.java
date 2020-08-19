package com.featureGating;

import com.featureGating.conditionEvaluator.ExpressionEvaluator;
import com.featureGating.conditionEvaluator.ExpressionType;
import com.featureGating.conditionEvaluator.tokenizers.SpaceTokenizer;
import com.featureGating.offers.Feature;

import java.util.HashMap;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        Map<String, Object> user_1 = new HashMap<String, Object>();
        user_1.put("name", "Ankit Kumar");
        user_1.put("past_order_amount", 11000.0);
        user_1.put("age", 24.0);
        user_1.put("gender", "Male");

        String exp1 = "( age > 25 ) AND ( gender == \"Male\" OR past_order_amount > 10000 )";
        String exp2 = "( age > 25 AND gender == \"Male\" ) OR ( past_order_amount > 10000 )";

        System.out.println("Condition: " + exp1);
        Feature testFeature1 = new Feature("TestFeature",
                new ExpressionEvaluator(new SpaceTokenizer(exp1), ExpressionType.INFIX));
        boolean isFeatureActive1 = testFeature1.isAllowed(user_1);
        System.out.println("Feature Enabled: " + isFeatureActive1);

        System.out.println("Condition: " + exp2);
        Feature testFeature2 = new Feature("TestFeature",
                new ExpressionEvaluator(new SpaceTokenizer(exp2), ExpressionType.INFIX));
        boolean isFeatureActive2 = testFeature2.isAllowed(user_1);
        System.out.println("Feature Enabled: " + isFeatureActive2);
    }
}
