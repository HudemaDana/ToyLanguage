package Model.Statement.BasicStmt;

import Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;

public class NopStmt implements IStmt {


    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString(){
        return "";
    }
}
