package Repository;

import Exception.MyException;
import Model.State.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Repository implements IRepository {

    List<PrgState> programStates;
    String logFilePath;

    public Repository() {

        programStates = new LinkedList<>();
        logFilePath = "";
    }

    public Repository(String lfPath) {
        programStates = new LinkedList<>();
        logFilePath = lfPath;
    }

    public Repository(PrgState state,String lfPath) {
        programStates = new LinkedList<>();
        programStates.add(state);
        logFilePath = lfPath;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public List<PrgState> getPrgStates() {
        return programStates;
    }

    @Override
    public PrgState getPrgStateWithId(int id) {
        if (id >= 0 && id < programStates.size())
            return programStates.get(id);
        return null;
    }

    public void setPrgList(List<PrgState> prgStates){
        programStates = prgStates;
    }

    @Override
    public void addPrgState(PrgState prg) {
        programStates.add(prg);
    }

    @Override
    public void removePrgState(int id) {
        if (getPrgStateWithId(id) != null) {
            programStates.remove(id);
        }
    }

    @Override
    public void logPrgStateExec(PrgState state) throws MyException {
        PrintWriter logFile;
        try {
            logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        } catch (IOException e) {
            throw new MyException(e.toString());
        }
        logFile.flush();
        logFile.print(state);
        logFile.close();
    }
}
