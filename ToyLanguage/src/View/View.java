package View;

import Controller.Controller;
import Exception.*;
import Model.Exp.*;
import Model.State.PrgState;
import Model.Statement.*;
import Model.Statement.BasicStmt.*;
import Model.Type.*;
import Model.Value.*;

import java.util.Scanner;

public class View {

    Controller _controller;

    public View(Controller controller) {
        _controller = controller;
    }

    public void menu() throws EmptyCollectionException {
        IStmt ex1 = new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        IStmt ex2 = new CompStmt(new VarDecl("a", new IntType()), new CompStmt(new VarDecl("b", new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(1, new ValueExp(new IntValue(2)), new ArithExp(3, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))), new CompStmt(new AssignStmt("b", new ArithExp(1, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()), new CompStmt(new VarDecl("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        while (true) {
            System.out.println("Choose a command to run from 1 to 3: ");
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            int s = Integer.parseInt(str);

            System.out.println("Do you want to be displayed step by step? --> type 1 if you want to");
            str = scanner.nextLine();
            int flag = Integer.parseInt(str);
            if (s == 1) {
                try {
                    PrgState prgState = new PrgState(ex1);
                    _controller.addPrgState(prgState);
                    _controller.allStep();
                    _controller.removePrgState(0);
                } catch (EmptyCollectionException | MyException e) {
                    System.out.println(e);
                }
            } else if (s == 2) {
                try {
                    PrgState prgState = new PrgState(ex2);
                    _controller.addPrgState(prgState);
                    _controller.allStep();
                    _controller.removePrgState(0);
                } catch (EmptyCollectionException | MyException e) {
                    System.out.println(e);
                }
            } else if (s == 3) {
                try {
                    PrgState prgState = new PrgState(ex3);
                    _controller.addPrgState(prgState);
                    _controller.allStep();
                    _controller.removePrgState(0);
                } catch (EmptyCollectionException | MyException e) {
                    System.out.println(e);
                }
            } else if (s == 0) {
                return;
            } else {
                System.out.println("Invalid input. Please try again");
            }
        }
    }

}
