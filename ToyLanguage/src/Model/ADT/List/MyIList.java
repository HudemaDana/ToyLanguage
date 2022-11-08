package Model.ADT.List;

import java.util.LinkedList;

public interface MyIList<T> {

    LinkedList<T> getContent();
    boolean isEmpty();

    void addElem(T elem);
    void removeElem(T elem);
}

