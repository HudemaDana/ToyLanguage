package Model.Exp;

import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.ExpressionException;
import Model.ADT.Dictionary.MyIDictionary;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op;
        //1 --> and
        //2 --> or
    public LogicExp(Exp exp1, Exp exp2, int operation){
        e1 = exp1;
        e2 = exp2;
        op = operation;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl) throws ExpressionException{
        Value v1 = e1.eval(tbl);
        if(v1.getType().equals(new BoolType()))
        {
            Value v2 = e2.eval(tbl);
            if(v2.getType().equals(new BoolType())){
                BoolValue bv1 = (BoolValue)e1;
                BoolValue bv2 = (BoolValue)e2;

                if(op == 1){ return new BoolValue(bv1.getValue() && bv2.getValue());}
                else if(op == 2){ return new BoolValue(bv1.getValue() || bv2.getValue());}
                else
                    throw new ExpressionException("operation doesn't exist");
            }
            else
                throw new ExpressionException("Second operand is not a bool");

        }
        else
            throw new ExpressionException("First operand is not a bool");

    }

    @Override
    public String toString(){
        String str = "";

        if(op == 1)
            str = " && ";
        else if(op == 2)
            str = " || ";

        return e1.toString()+ str +e2.toString();
    }
}
