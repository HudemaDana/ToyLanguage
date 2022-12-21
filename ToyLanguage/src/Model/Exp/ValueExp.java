package Model.Exp;

import Model.ADT.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Model.ADT.Dictionary.MyIDictionary;
import Exception.*;

public class ValueExp implements Exp {
    Value v;

    public ValueExp(Value val) {
        v = val;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws ExpressionException {
        return v;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException {
        return v.getType();
    }

    @Override
    public String toString() {
        return v.toString();
    }

}
