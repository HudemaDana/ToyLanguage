package Repository;

import Model.State.PrgState;
import Exception.*;

import java.util.List;

public interface IRepository {
    List<PrgState> getPrgStates();

    PrgState getPrgStateWithId(int id);

    void addPrgState(PrgState prg);

    void removePrgState(int id);

    void logPrgStateExec() throws MyException;
}
