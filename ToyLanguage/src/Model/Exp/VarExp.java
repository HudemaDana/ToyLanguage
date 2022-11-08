package Model.Exp;
import Model.Value.Value;
import Exception.ExpressionException;
import Model.ADT.Dictionary.MyIDictionary;

public class VarExp implements Exp{
    String id;
    public VarExp(String Id){
        id = Id;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl) throws ExpressionException{
        return tbl.lookup(id);
    }

    @Override
    public String toString(){
        return id;
    }
}
