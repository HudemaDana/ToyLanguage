package Model.Statement.BasicStmt;

import Exception.*;
import Exception.ExpressionException;
import Model.ADT.Dictionary.MyIDictionary;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.Type;
import Model.Value.Value;

public class AssignStmt implements IStmt {

    String id;
    Exp exp;

    public AssignStmt(String variableName,Exp e){
        id = variableName;
        exp = e;
    }

    public AssignStmt(AssignStmt stmt){
        id = stmt.id;
        exp = stmt.exp;
    }
    @Override
    public PrgState execute(PrgState state) throws MyException{
        MyIDictionary<String, Value> newSymTable = state.getSymTable();

        if(newSymTable.isVariableDefined(id)){
            try {
                Value v1 = exp.eval(newSymTable);
                Type idType = newSymTable.lookup(id).getType();
                if (v1.getType().equals(idType)) {
                    newSymTable.update(id, v1);
                } else
                    throw new MyException("Id doesn't match the type");
            }
            catch(ExpressionException | VariableException e){
                throw new MyException(e.toString());
            }
        }
        else
            throw new MyException("Id doesn't exist in SymbolTable");
  
        state.setSymTable(newSymTable);
        return state;

    }

    @Override
    public String toString(){

        return id + "=" + exp.toString();
    }


    public Exp getExp() {
        return exp;
    }

    public String getId() {
        return id;
    }

    public void setExp(Exp exp) {
        this.exp = exp;
    }

    public void setId(String id) {
        this.id = id;
    }
}
