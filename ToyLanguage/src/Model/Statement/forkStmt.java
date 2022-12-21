package Model.Statement;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyStack;
import Model.State.PrgState;
import Exception.*;
import Model.Type.Type;

public class forkStmt implements IStmt {

    IStmt stmt;

    public forkStmt(IStmt s) {
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        return new PrgState(new MyStack<>(), state.getSymTable().clone(),state.getOut(), state.getHeap(), state.getFileTable(), stmt);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return stmt.typecheck(typeEnv);
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}
