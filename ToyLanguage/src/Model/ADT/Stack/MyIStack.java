package Model.ADT.Stack;

import Exception.EmptyCollectionException;

import java.util.List;

public interface MyIStack<T> {
    T pop() throws EmptyCollectionException;
    void push(T v);
    T top() throws EmptyCollectionException;
    boolean isEmpty();
    List getValues();

}
