package Model.Statement.HeapStmt;

import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyIHeap;
import Model.Exp.Exp;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import Exception.*;

public class WriteHeapStmt implements IStmt {
    String var_name;
    Exp exp;

    public WriteHeapStmt(String name, Exp e) {
        var_name = name;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap<Value> heap = state.getHeap();

        if (symTable.isVariableDefined(var_name)) {
            Value val = symTable.lookup(var_name);
            if (val instanceof RefValue) {
                int address = ((RefValue) val).getAddress();
                if (heap.get(address) != null) { // check if there's anything at that address
                    Value evalValue = null;
                    try {
                        evalValue = exp.eval(symTable, heap);
                    } catch (ExpressionException e) {
                        throw new RuntimeException(e);
                    }
                    if (evalValue.getType().equals(((RefValue) val).getLocationType())) { //check if the types are equal and update the value at that address in the heap
                        heap.put(address, evalValue);
                    } else
                        throw new MyException("Incompatible types.");
                } else
                    throw new MyException("Address is not a key in the heap.");
            } else
                throw new MyException("Value not of type Reference type");
        } else
            throw new MyException("Variable not defined.");
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
            throw new MyException("WRITE TO HEAP stmt: right hand side and left hand side have different types ");

    }

    @Override
    public String toString() {
        return "wH(" + var_name + "," + exp.toString() + ")";
    }
}
