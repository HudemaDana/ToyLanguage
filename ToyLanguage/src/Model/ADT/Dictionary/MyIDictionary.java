package Model.ADT.Dictionary;

import Exception.VariableException;
import java.util.Map;

public interface MyIDictionary<T,E> {
    E lookup(T id);
    Boolean isVariableDefined(T id);

    Map<T,E> getContent();

    void add(T id, E elem) throws VariableException;
    void remove(T id) throws VariableException;
    void update(T id, E elem) throws VariableException;
    MyIDictionary<T,E> clone();
}
