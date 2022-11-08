package Model.Statement.BasicStmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exp.Exp;
import Model.State.PrgState;
import Exception.MyException;
import Exception.ExpressionException;
import Model.Statement.IStmt;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStmt implements IStmt {
    Exp ifExp;
    IStmt thenStmt, elseStmt;

    public IfStmt(Exp exp,IStmt s1,IStmt s2){
        ifExp = exp;
        thenStmt = s1;
        elseStmt = s2;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> newStack = state.getStk();
        MyIDictionary<String,Value> newSymTbl = state.getSymTable();
        try {
            Value condition = ifExp.eval(newSymTbl);
            if (condition.getType().equals(new BoolType())) {
                BoolValue boolCondition = (BoolValue) condition;
                if (boolCondition.getValue() == true)
                    newStack.push(thenStmt);
                else
                    newStack.push(elseStmt);
            } else
                throw new MyException("this is not a logic expression");
        }
        catch(ExpressionException e){
            throw new MyException(e.toString());
        }
        state.setExeStack(newStack);
        return state;
    }

    @Override
    public String toString() {
        return "(IF("+ ifExp.toString()+") THEN(" +thenStmt.toString()+") ELSE("+elseStmt.toString()+"))";
    }

    public IStmt getElseStmt() {
        return elseStmt;
    }

    public IStmt getThenStmt() {
        return thenStmt;
    }

    public Exp getIfExp() {
        return ifExp;
    }

    public void setElseStmt(IStmt elseStmt) {
        this.elseStmt = elseStmt;
    }

    public void setIfExp(Exp ifExp) {
        this.ifExp = ifExp;
    }

    public void setThenStmt(IStmt thenStmt) {
        this.thenStmt = thenStmt;
    }
}
