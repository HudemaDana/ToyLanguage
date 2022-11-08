package Model.Statement.BasicStmt;

import Exception.MyException;
import Model.State.PrgState;
import Model.Statement.IStmt;

public class NopStmt implements IStmt {


    @Override
    public PrgState execute(PrgState state) throws MyException {
        return state;
    }

    @Override
    public String toString(){
        return "";
    }
}
