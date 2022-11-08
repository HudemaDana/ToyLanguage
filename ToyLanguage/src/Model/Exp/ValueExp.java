package Model.Exp;

import Exception.ExpressionException;
import Model.Value.Value;
import Model.ADT.Dictionary.MyIDictionary;

public class ValueExp implements Exp{
    Value v;
    public ValueExp(Value val){
        v = val;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl) throws ExpressionException{
        return v;
    }

    @Override
    public String toString(){
        return v.toString();
    }

}
