package Model.Statement;

import Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.State.PrgState;
import Model.Type.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
    MyIDictionary<String,Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}
