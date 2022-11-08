package Controller;

import Model.ADT.Stack.MyIStack;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Repository.IRepository;
import Exception.EmptyCollectionException;
import Exception.MyException;

import java.util.List;

public class Controller {

    IRepository repo;

    public Controller(IRepository repository) {
        repo = repository;
    }

    public IRepository getRepo() {
        return repo;
    }

    public void setRepo(IRepository repo) {
        this.repo = repo;
    }

    public void addPrgState(PrgState prg) {
        repo.addPrgState(prg);
    }

    public void removePrgState(int id) {
        repo.removePrgState(id);
    }

    public PrgState oneStep(PrgState state) throws EmptyCollectionException, MyException {
        MyIStack<IStmt> stk = state.getStk();

        if (stk.isEmpty())
            throw new EmptyCollectionException("Prg state is empty");
        else {
            IStmt stmt = stk.pop();
            return stmt.execute(state);
        }
    }

    public void allStep(int flag) throws EmptyCollectionException, MyException {
        List<PrgState> prg = repo.getPrgStates();
        repo.logPrgStateExec();
        for (PrgState prgState : prg) {
            while (!prgState.getStk().isEmpty()) {
                oneStep(prgState);
                repo.logPrgStateExec();
                if (flag == 1)
                    System.out.println(prgState.toString());
            }
            if (flag != 1) {
                System.out.println(prgState.toString());
            }
        }
    }

    public String displayState(PrgState state) {
        return state.toString();
    }
}
