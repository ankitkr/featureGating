package com.featureGating.conditionEvaluator.operators;

import com.featureGating.conditionEvaluator.operands.Operand;
import com.featureGating.conditionEvaluator.operators.exceptions.OperandMismatchException;

import java.util.List;

public abstract class Operator {
    public abstract String getName();
    public abstract String getSymbol();
    public abstract Integer getPrecedence();
    public abstract Integer getOperandCount();

    public abstract boolean apply(List<Operand> operands) throws OperandMismatchException;

    public Boolean validOperands(List<Operand> operands){
        // checks whether all the operands are of same type
        if (operands.isEmpty()) {
            return false;
        }

        if(this.getOperandCount() != Double.NaN) {
            Integer requiredOperandCount = operands.size();
            if(!this.getOperandCount().equals(requiredOperandCount))
                return false;
        }

        String data_type = null;
        for (Operand operand: operands){
            if (data_type == null)
                data_type = operand.getName();

            if(!data_type.equals(operand.getName()))
                return false;
        }

        return true;
    }

}
