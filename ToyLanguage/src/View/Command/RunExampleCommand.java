package View.Command;

import Controller.Controller;
import Exception.*;
import Model.ADT.Dictionary.MyDictionary;
import Model.Statement.IStmt;
import Model.Type.Type;

public class RunExampleCommand extends ACommand{
    Controller ctr;
    public RunExampleCommand(String key, String description, Controller c){
        super(key, description);
        ctr = c;
    }

    @Override
    public void execute() {
        try{
            IStmt arr = (IStmt) ctr.getRepo().getPrgStates().get(0).getStk().getValues().get(0);
            arr.typecheck(new MyDictionary<String, Type>());
            ctr.allStep();
        } catch (MyException|EmptyCollectionException e) {
            throw new RuntimeException(e);
        }
    }
}
