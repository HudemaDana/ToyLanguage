package Model.Statement.BasicStmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Exception.MyException;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;

public class CompStmt implements IStmt {
    IStmt stmt1;
    IStmt stmt2;

    public CompStmt(IStmt i1, IStmt i2) {
        stmt1 = i1;
        stmt2 = i2;
    }

    public CompStmt(CompStmt stmt) {
        stmt1 = stmt.stmt1;
        stmt2 = stmt.stmt2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newExecStack = state.getStk();
        newExecStack.push(stmt2);
        newExecStack.push(stmt1);

        //state.setExeStack(newExecStack);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return stmt2.typecheck(stmt1.typecheck(typeEnv));
    }

    @Override
    public String toString() {

        return "(" + stmt1.toString() + ";" + stmt2.toString() + ")";
    }

    public IStmt getStmt1() {
        return stmt1;
    }

    public IStmt getStmt2() {
        return stmt2;
    }

    public void setStmt1(IStmt stmt) {
        this.stmt1 = stmt;
    }

    public void setStmt2(IStmt s2) {
        this.stmt2 = s2;
    }
}
