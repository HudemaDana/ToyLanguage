package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.*;

public class ReadHeapExp implements Exp {
    private Exp exp;

    public ReadHeapExp(Exp e) {
        exp = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws ExpressionException {

        Value evaluationValue = this.exp.eval(table, heap);
        if (evaluationValue instanceof RefValue) {
            //downcast to ref value first
            int address = ((RefValue) evaluationValue).getAddress();
            //take the value from the heap if it exists
            Value valueFromHeap = heap.get(address);
            if (valueFromHeap != null) {
                return valueFromHeap;
            } else
                throw new ExpressionException("Address doesnt have a value.");
        } else
            throw new ExpressionException("Value is not of type reference value.");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException {
        Type typ = exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else throw new ExpressionException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }
}
