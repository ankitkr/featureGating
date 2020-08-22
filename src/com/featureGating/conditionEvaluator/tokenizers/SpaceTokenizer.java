package com.featureGating.conditionEvaluator.tokenizers;

import java.util.Arrays;
import java.util.LinkedList;

public class SpaceTokenizer extends Tokenizer{
    public SpaceTokenizer(String expression) {
        this.q = new LinkedList<>();
        this.q.addAll(Arrays.asList(expression.split(" ")));
    }

    @Override
    public String getNextToken() {
        return this.q.poll();
    }

    @Override
    public Integer getTokenQueueSize() {
        return this.q.size();
    }
}
