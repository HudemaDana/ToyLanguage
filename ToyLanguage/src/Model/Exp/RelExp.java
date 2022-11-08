package Model.Exp;

import Model.ADT.Dictionary.MyIDictionary;
import Model.Type.IntType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import Exception.*;
public class RelExp implements Exp{
    Exp e1;
    Exp e2;
    int operation;
        //1 --> <
        //2 --> <=
        //3 --> ==
        //4 --> !=
        //5 --> >=
        //6 --> >

    public RelExp(int op,Exp e1, Exp e2){
        operation =op;
        this.e1 = e1;
        this.e2 = e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl) throws ExpressionException {
        Value v1,v2;
        v1 = e1.eval(tbl);
        if(v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl);
            if(v2.getType().equals(new IntType())) {
                IntValue iv1 = (IntValue)v1;
                IntValue iv2 = (IntValue)v2;

                int n1,n2;
                n1 = iv1.getValue();
                n2 = iv2.getValue();

                if(operation == 1) { return new BoolValue(n1<n2); }
                else if(operation == 2) { return new BoolValue(n1<=n2); }
                else if(operation == 3) { return new BoolValue(n1==n2); }
                else if(operation == 4) { return new BoolValue(n1!=n2); }
                else if(operation == 5) { return new BoolValue(n1>=n2); }
                else if(operation == 6) { return new BoolValue(n1>n2); }
                else
                    throw new ExpressionException("Operation doesn't exist");

            }
            else
                throw new ExpressionException("Second operand is not an integer");
        }
        else
            throw new ExpressionException("First operand is not an integer");
    }
}
