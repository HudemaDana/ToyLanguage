package Model.Exp;

import Model.ADT.Heap.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import Model.ADT.Dictionary.MyIDictionary;
import Exception.*;

public class VarExp implements Exp {
    String id;

    public VarExp(String Id) {
        id = Id;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws ExpressionException {
        return tbl.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException {
        return typeEnv.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
