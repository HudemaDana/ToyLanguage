package Model.ADT.Heap;

import java.util.Map;

public interface MyIHeap<T> {

    int allocate(T val);

    T get(int addr);

    void put(int addr, T val);

    T deallocate(int addr);

    Map<Integer, T> getContent();

    void setContent(Map<Integer, T> content);
}
