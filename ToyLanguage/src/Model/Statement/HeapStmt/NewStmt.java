package Model.Statement.HeapStmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Exp.Exp;
import Model.State.PrgState;
import Exception.*;
import Model.Statement.IStmt;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;

public class NewStmt implements IStmt {

    String var_name;
    Exp expression;

    public NewStmt(String name, Exp e) {
        var_name = name;
        expression = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();

        if (symTable.isVariableDefined(var_name)) {
            Value val = symTable.lookup(var_name);
            if (val instanceof RefValue) {
                Value expValue = null;
                try {
                    expValue = expression.eval(symTable, heap);
                    if (expValue.getType().equals(((RefValue) val).getLocationType())) {
                        int location = state.getHeap().allocate(expValue);
                        try {
                            symTable.update(var_name, new RefValue(location, expValue.getType()));
                        } catch (VariableException e) {
                            throw new RuntimeException(e);
                        }
                    } else
                        throw new MyException("Not equal");
                } catch (ExpressionException e) {
                    throw new MyException(e.toString());
                }
            } else
                throw new MyException("Invalid value. Not a reference value");
        } else
            throw new MyException("Variable not defined");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var_name);
        Type typexp = null;
        try {
            typexp = expression.typecheck(typeEnv);
        } catch (ExpressionException e) {
            throw new MyException(e.getMessage());
        }
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" + var_name + "," + expression.toString() + ")";
    }
}
