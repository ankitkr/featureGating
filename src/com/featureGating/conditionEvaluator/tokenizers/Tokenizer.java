package com.featureGating.conditionEvaluator.tokenizers;

import java.util.LinkedList;
import java.util.Queue;

public abstract class Tokenizer {
    Queue<String> q;

    public abstract String getNextToken();
    public abstract Integer getTokenQueueSize();
}
