package Model.Statement.BasicStmt;

import Exception.MyException;
import Exception.VariableException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;
import Model.Value.Value;

public class VarDecl implements IStmt {
    String varName;
    Type varType;

    public VarDecl(String name, Type type) {
        varName = name;
        varType = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {

        MyIDictionary<String, Value> newSymTbl = state.getSymTable();

        if (newSymTbl.isVariableDefined(varName))
            throw new MyException("Var already exists");
        else {
            try {
                newSymTbl.add(varName, varType.defaultValue());
            } catch (VariableException e) {
                throw new MyException(e.toString());
            }
        }
        //state.setSymTable(newSymTbl);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        try {
            typeEnv.add(varName, varType);
        } catch (VariableException e) {
            throw new MyException(e.getMessage());
        }
        return typeEnv;
    }

    @Override
    public String toString() {
        return varType.toString() + " " + varName;
    }

    public String getVarName() {
        return varName;
    }

    public Type getVarType() {
        return varType;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }

    public void setVarType(Type varType) {
        this.varType = varType;
    }
}
