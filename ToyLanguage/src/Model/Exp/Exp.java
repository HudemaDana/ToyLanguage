package Model.Exp;

import Model.ADT.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Model.ADT.Dictionary.MyIDictionary;
import Exception.*;

public interface Exp{
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Value> heap) throws ExpressionException;
    Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException;
}
