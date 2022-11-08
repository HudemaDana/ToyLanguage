package Model.Statement;

import Exception.MyException;
import Model.State.PrgState;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException;
}
