package Model.Statement.BasicStmt;

import Exception.ExpressionException;
import Exception.MyException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.Stack.MyIStack;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Value.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp e){

        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIStack<IStmt> newStack = state.getStk();
        MyIDictionary<String,Value> newSymTbl = state.getSymTable();
        MyIList<Value> newOut = state.getOut();
        try {
            newOut.addElem(exp.eval(newSymTbl));
            state.setOut(newOut);
        }catch(ExpressionException e){
            throw new MyException(e.toString());
        }
        return state;
    }

    @Override
    public String toString(){

        return "Print("+exp.toString()+")";
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public Exp getExp() {
        return exp;
    }
}
