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
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt {

    Exp exp;

    public openRFile(Exp e) {
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
                if (!newFileTbl.isVariableDefined(s1.getValue())) {
                    BufferedReader objReader = new BufferedReader(new FileReader(s1.getValue()));
                    newFileTbl.add(s1.getValue(), objReader);
                } else
                    throw new MyException("File already defined and opened");

            } else
                throw new MyException("Can't open a file which doesn't have the name a string");

        } catch (ExpressionException | FileNotFoundException | VariableException e) {
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
        return "openRFile(" + exp + ")";
    }

}
