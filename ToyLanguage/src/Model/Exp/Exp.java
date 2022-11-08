package Model.Exp;

import Exception.ExpressionException;
import Model.Value.Value;
import Model.ADT.Dictionary.MyIDictionary;

public interface Exp{
    Value eval(MyIDictionary<String,Value> tbl) throws ExpressionException;
}
