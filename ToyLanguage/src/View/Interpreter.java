package View;

import Controller.Controller;
import Model.Exp.*;
import Model.State.PrgState;
import Model.Statement.BasicStmt.*;
import Model.Statement.FileStmt.closeRFile;
import Model.Statement.FileStmt.openRFile;
import Model.Statement.FileStmt.readFile;
import Model.Statement.IStmt;
import Model.Statement.HeapStmt.NewStmt;
import Model.Statement.HeapStmt.WriteHeapStmt;
import Model.Statement.forkStmt;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Repository.IRepository;
import Repository.Repository;
import View.Command.ExitCommand;
import View.Command.RunExampleCommand;

public class Interpreter {
    public static void main(String[] args) {
        IStmt ex1 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(ex1);
        IRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        IStmt ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(ex2);
        IRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()), new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(ex3);
        IRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        IStmt ex4 = new CompStmt(
                new VarDecl("varf", new StringType()), new CompStmt(
                new AssignStmt("varf", new ValueExp(new StringValue("test.in"))), new CompStmt(
                new openRFile(new VarExp("varf")), new CompStmt(
                new VarDecl("varc", new IntType()), new CompStmt(
                new readFile(new VarExp("varf"), "varc"), new CompStmt(
                new PrintStmt(new VarExp("varc")), new CompStmt(
                new readFile(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new closeRFile(new VarExp("varf"))))))))));

        PrgState prg4 = new PrgState(ex4);
        IRepository repo4 = new Repository(prg4, "test.out");
        Controller ctr4 = new Controller(repo4);

        IStmt ex5 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(10))), new CompStmt(new IfStmt(new RelExp(1, new VarExp("a"), new VarExp("v")), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        PrgState prg5 = new PrgState(ex5);
        IRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);
        ctr5.addPrgState(prg5);

        IStmt ex6 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(
                new WhileStmt(new RelExp(6, new VarExp("v"), new ValueExp(new IntValue(0))), new CompStmt(new PrintStmt(
                        new VarExp("v")), new AssignStmt("v", new ArithExp(2, new VarExp("v"), new ValueExp(new IntValue(1)))))),
                new PrintStmt(new VarExp("v")))));

        PrgState prg6 = new PrgState(ex6);
        IRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        IStmt ex7 = new CompStmt(new VarDecl("v", new RefType(new IntType())), new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new CompStmt(new WriteHeapStmt("v", new ValueExp(
                        new IntValue(30))), new PrintStmt(new ArithExp(1, new ReadHeapExp(new VarExp("v")), new ValueExp(
                        new IntValue(5))))))));

        PrgState prg7 = new PrgState(ex7);
        IRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);
        ctr7.addPrgState(prg7);

        IStmt ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())), new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))), new CompStmt(new NewStmt("a", new VarExp("v")),
                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ReadHeapExp(new ReadHeapExp(
                                new VarExp("a")))))))));

        PrgState prg8 = new PrgState(ex8);
        IRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        IStmt forked = new CompStmt(new WriteHeapStmt("a",new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeapExp(new VarExp("a"))))));

        IStmt ex9 = new CompStmt(new VarDecl("v", new IntType()),
                new CompStmt(new VarDecl("a",new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(10))),
                                new CompStmt(new NewStmt("a",new ValueExp(new IntValue(22))),
                                        new CompStmt(new forkStmt(forked),new CompStmt(new PrintStmt(new VarExp("v")),new PrintStmt(new ReadHeapExp(new VarExp("a"))))))
                        )));

        PrgState prg9 = new PrgState(ex9);
        IRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExampleCommand("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExampleCommand("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExampleCommand("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExampleCommand("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExampleCommand("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExampleCommand("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExampleCommand("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExampleCommand("9", ex9.toString(), ctr9));
        menu.show();
    }
}
