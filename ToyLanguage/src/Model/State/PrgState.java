package Model.State;

import Model.ADT.Dictionary.MyDictionary;
import Model.ADT.Dictionary.MyIDictionary;
import Model.ADT.Heap.MyHeap;
import Model.ADT.Heap.MyIHeap;
import Model.ADT.List.MyIList;
import Model.ADT.List.MyList;
import Model.ADT.Stack.MyIStack;
import Model.ADT.Stack.MyStack;
import Model.Statement.IStmt;
import Model.Value.Value;
import Exception.*;

import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;

    MyIHeap<Value> heap;

    static int id;
    static int globalId = 1;
    //IStmt   originalProgram;

    public PrgState(IStmt stmt) {
        exeStack = new MyStack<>();
        exeStack.push(stmt);

        symTable = new MyDictionary<>();
        out = new MyList<>();
        fileTable = new MyDictionary<>();
        heap = new MyHeap<>();
        id = 1;
    }

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, MyIHeap<Value> hp, MyIDictionary<String, BufferedReader> fTable, IStmt stmt) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = fTable;
        heap = hp;
        //originalProgram = clone((Object)oProg);
        if(stmt!=null)
            stk.push(stmt);
        id = getGlobalID();

    }

    public Boolean isNotCompleted() {
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws EmptyCollectionException, MyException {
        if (!exeStack.isEmpty()) {
            //throw new MyException("Program state stack is empty");
            IStmt crtStmt = exeStack.pop();
            return crtStmt.execute(this);
        }
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public synchronized int getGlobalID() {
        globalId*=10;
        return globalId;
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

    public MyIHeap<Value> getHeap() {
        return heap;
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

    public void setHeap(MyIHeap<Value> heap) {
        this.heap = heap;
    }

    @Override
    public String toString() {
        return "Program State with id:" + id + "\n" +
                "----------------------------------\n" +
                "Execution Stack\n" + exeStack.toString() +
                "\n\n" +
                "Symbol Table\n" + symTable.toString() +
                "\n\n" +
                "Out List\n" + out.toString() +
                "\n\n" +
                "File Table\n" + fileTable.toString() +
                "\n\n" +
                "Heap\n" + heap.toString() +
                "\n----------------------------------\n\n\n";
    }
}
