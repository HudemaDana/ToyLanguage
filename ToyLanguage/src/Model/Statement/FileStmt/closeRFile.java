package Model.Statement.FileStmt;

import Exception.ExpressionException;
import Exception.MyException;
import Exception.VariableException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt {
    Exp exp;

    public closeRFile(Exp e) {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, BufferedReader> newFileTbl = state.getFileTable();
        MyIDictionary<String, Value> newSymTbl = state.getSymTable();

        try {
            Value fileExpression = exp.eval(newSymTbl, state.getHeap());
            if (fileExpression.getType().equals(new StringType())) {
                StringValue s1 = (StringValue) fileExpression;
                if (newFileTbl.isVariableDefined(s1.getValue())) {
                    BufferedReader objReader = newFileTbl.lookup(s1.getValue());
                    objReader.close();
                    newFileTbl.remove(s1.getValue());
                } else
                    throw new MyException("File not found");

            } else
                throw new MyException("Can't open a file which doesn't have the name a string");

        } catch (ExpressionException | IOException | VariableException e) {
            throw new MyException(e.toString());
        }
        //state.setFileTable(newFileTbl);
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
        return "closeRFile(" + exp.toString() + ")";
    }
}
