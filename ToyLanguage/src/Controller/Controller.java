package Controller;

import Model.ADT.Stack.MyIStack;
import Model.State.PrgState;
import Model.Statement.IStmt;
import Model.Value.RefValue;
import Model.Value.Value;
import Repository.IRepository;
import Exception.EmptyCollectionException;
import Exception.MyException;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class Controller {

    IRepository repo;
    ExecutorService executor;

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

    public List<PrgState> removeCompletedPrg(List<PrgState> inPrgList) {
        return inPrgList.stream().filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer, Value> heap) {
        return heap.entrySet()
                .stream()
                .filter(e -> addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    static public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap) {
        List<Integer> result = new ArrayList<>();
        result.addAll(symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList()));

        result.addAll(heap.stream().filter(v -> v instanceof RefValue).map(v -> ((RefValue) v).getAddress()).collect(Collectors.toList()));

        return result;
    }

    public void callGarbageCollector(List<PrgState> prgStates) {
        prgStates.forEach(
                p -> {
                    p.getHeap().setContent(safeGarbageCollector(getAddrFromSymTable(p.getSymTable().getContent().values(), p.getHeap().getContent().values()), p.getHeap().getContent()));
                }
        );
    }

    public void oneStep() throws MyException {
        executor = Executors.newFixedThreadPool(2);
        repo.setPrgList(removeCompletedPrg(repo.getPrgStates()));
        List<PrgState> programStates = repo.getPrgStates();
        if(programStates.size() > 0)
        {
            oneStepForAllPrg(repo.getPrgStates());
            repo.setPrgList(removeCompletedPrg(repo.getPrgStates()));
            executor.shutdownNow();
            callGarbageCollector(programStates);
        }

    }

    public void oneStepForAllPrg(List<PrgState> prgList) {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        List<Callable<PrgState>> callList = prgList.stream().map((PrgState p) -> (Callable<PrgState>) (p::oneStep)).collect(Collectors.toList());

        try {
            List<PrgState> newPrgList = executor.invokeAll(callList).stream().map(future -> {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }
            )
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            prgList.addAll(newPrgList);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
            prgList.forEach(prg -> {
                try {
                    repo.logPrgStateExec(prg);
                } catch (MyException e) {
                    throw new RuntimeException(e);
                }
            });
            repo.setPrgList(prgList);


    }

    public void allStep() throws EmptyCollectionException, MyException {
        executor = Executors.newFixedThreadPool(1);
        List<PrgState> prgList = removeCompletedPrg(repo.getPrgStates());
        while (prgList.size() > 0) {
            callGarbageCollector(prgList);
            oneStepForAllPrg(prgList);
            prgList = removeCompletedPrg(repo.getPrgStates());
        }
        executor.shutdownNow();
        prgList = removeCompletedPrg(repo.getPrgStates());
        repo.setPrgList(prgList);

    }

    public String displayState(PrgState state) {
        return state.toString();
    }
}
