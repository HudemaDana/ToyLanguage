package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import Exception.*;

public class RelExp implements Exp {
    Exp e1;
    Exp e2;
    int operation;
    //1 --> <
    //2 --> <=
    //3 --> ==
    //4 --> !=
    //5 --> >=
    //6 --> >

    public RelExp(int op, Exp e1, Exp e2) {
        operation = op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Value> heap) throws ExpressionException {
        Value v1, v2;
        v1 = e1.eval(tbl, heap);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,heap);
            if (v2.getType().equals(new IntType())) {
                IntValue iv1 = (IntValue) v1;
                IntValue iv2 = (IntValue) v2;

                int n1, n2;
                n1 = iv1.getValue();
                n2 = iv2.getValue();

                if (operation == 1) {
                    return new BoolValue(n1 < n2);
                } else if (operation == 2) {
                    return new BoolValue(n1 <= n2);
                } else if (operation == 3) {
                    return new BoolValue(n1 == n2);
                } else if (operation == 4) {
                    return new BoolValue(n1 != n2);
                } else if (operation == 5) {
                    return new BoolValue(n1 >= n2);
                } else if (operation == 6) {
                    return new BoolValue(n1 > n2);
                } else
                    throw new ExpressionException("Operation doesn't exist");

            } else
                throw new ExpressionException("Second operand is not an integer");
        } else
            throw new ExpressionException("First operand is not an integer");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws ExpressionException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else throw new ExpressionException("second operand is not an integer");
        } else throw new ExpressionException("first operand is not an integer");
    }

    @Override
    public String toString(){
        String str;
        if(operation == 1)
            str = " < ";
        else if(operation == 2)
            str = " <= ";
        else if(operation == 3)
            str = " == ";
        else if(operation == 4)
            str = " != ";
        else if(operation == 5)
            str = " >= ";
        else
            str = " > ";
        return e1.toString()+str+e2.toString();
    }
}
