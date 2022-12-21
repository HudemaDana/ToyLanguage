package Model.Statement.FileStmt;

import Exception.ExpressionException;
import Exception.MyException;
import Exception.VariableException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt {

    Exp exp;
    String var_name;

    public readFile(Exp e, String name) {
        exp = e;
        var_name = name;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> newSymTbl = state.getSymTable();
        MyIDictionary<String, BufferedReader> newFileTbl = state.getFileTable();

        try {
            if (newSymTbl.isVariableDefined(var_name)) {
                if (newSymTbl.lookup(var_name).getType().equals(new IntType())) {
                    Value fileExpression = exp.eval(newSymTbl, state.getHeap());
                    if (fileExpression.getType().equals(new StringType())) {
                        StringValue s1 = (StringValue) fileExpression;
                        BufferedReader objReader = newFileTbl.lookup(s1.getValue());
                        if (objReader != null) {
                            String str = objReader.readLine();
                            if (str == null)
                                newSymTbl.update(var_name, new IntType().defaultValue());
                            else {
                                IntValue v = new IntValue(Integer.parseInt(str));
                                newSymTbl.update(var_name, v);
                            }

                        } else
                            throw new MyException("No entry associated to the file name");

                    } else
                        throw new MyException("Expression is not an int value");

                } else
                    throw new MyException("Variable is not an int");
            } else
                throw new MyException("Variable not defined");
        } catch (ExpressionException | IOException | VariableException e) {
            throw new RuntimeException(e);
        }

        //state.setSymTable(newSymTbl);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = null;
        try {
            typexp = exp.typecheck(typeEnv);
        } catch (ExpressionException e) {
            throw new MyException(e.getMessage());
        }
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("READ FILE stmt: right hand side and left hand side have different types ");

    }

    @Override
    public String toString() {
        return "readFile(" + exp.toString() + "," + var_name + ")";
    }
}
