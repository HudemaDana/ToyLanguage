package Model.Statement.BasicStmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Stack.MyIStack;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import Exception.*;

public class WhileStmt implements IStmt {
    Exp exp;
    IStmt statement;

    public WhileStmt(Exp e, IStmt stmt) {
        exp = e;
        statement = stmt;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symbolTable = state.getSymTable();

        Value result = null;
        try {
            result = exp.eval(symbolTable,state.getHeap());
        } catch (ExpressionException e) {
            throw new RuntimeException(e);
        }
        if (result.getType().equals(new BoolType())){
            BoolValue downcastedResult = (BoolValue)result;
            if (downcastedResult.getValue()==true){
                stk.push(new WhileStmt(exp,statement));
                stk.push(statement);
            }
        }
        else
            throw new MyException("Condition exp is not a boolean.");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = null;
        try {
            typexp = exp.typecheck(typeEnv);
        } catch (ExpressionException e) {
            throw new MyException(e.getMessage());
        }
        if (typexp.equals(new BoolType())) {
            statement.typecheck(typeEnv.clone());
            return typeEnv;
        }
        else
            throw new MyException("The condition of WHILE has not the type bool");
    }

    @Override
    public String toString() {
        return "(while(" + exp.toString() + ")" + statement.toString()+")";
    }

}
