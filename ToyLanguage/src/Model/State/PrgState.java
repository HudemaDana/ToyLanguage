package Model.State;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Statement.IStmt;
import Model.Value.Value;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;

    MyIDictionary<String, BufferedReader> fileTable;
    //IStmt   originalProgram; //optional field, but good to have

    public PrgState(IStmt stmt) {
        exeStack = new MyStack<>();
        exeStack.push(stmt);

        symTable = new MyDictionary<>();
        out = new MyList<>();
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt oProg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        // originalProgram = new IStmt(oProg);
        stk.push(oProg);
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }


    public void setExeStack(MyIStack<IStmt> newStack) {
        exeStack = newStack;
    }

    public void setSymTable(MyIDictionary<String, Value> newSymTable) {
        symTable = newSymTable;
    }

    public void setOut(MyIList<Value> newOut) {
        out = newOut;
    }

    public void setFileTable(MyIDictionary<String, BufferedReader> newFileTable) {
        fileTable = newFileTable;
    }

    @Override
    public String toString() {
        return "Program State\n" +
                "----------------------------------\n" +
                "Execution Stack\n" + exeStack.toString() +
                "\n----------------------------------\n" +
                "Symbol Table\n" + symTable.toString() +
                "\n----------------------------------\n" +
                "Out List\n" + out.toString() +
                "\n----------------------------------\n"+
                "File Table\n" + fileTable.toString() +
                "\n----------------------------------\n";
    }
}
