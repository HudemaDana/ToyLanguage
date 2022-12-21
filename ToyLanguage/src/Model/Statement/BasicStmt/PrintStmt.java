package Model.Statement.BasicStmt;

import Exception.*;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;
import Model.Value.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp e) {

        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, Value> newSymTbl = state.getSymTable();
        MyIList<Value> newOut = state.getOut();
        try {
            newOut.addElem(exp.eval(newSymTbl, state.getHeap()));
            //state.setOut(newOut);
        } catch (ExpressionException e) {
            throw new MyException(e.toString());
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        try {
            exp.typecheck(typeEnv);
        } catch (ExpressionException e) {
            throw new MyException(e.getMessage());
        }
        return typeEnv;
    }


    @Override
    public String toString() {

        return "Print(" + exp.toString() + ")";
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }
}
