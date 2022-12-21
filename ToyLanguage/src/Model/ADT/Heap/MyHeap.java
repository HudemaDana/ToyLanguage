package Model.ADT.Heap;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<T> implements MyIHeap<T> {

    HashMap<Integer, T> map;
    int memory;

    public MyHeap() {
        map = new HashMap<Integer, T>();
        memory = 0;
    }

    @Override
    public int allocate(Object val) {
        memory++;
        map.put(memory, (T) val);
        return memory;
    }

    @Override
    public T get(int addr) {
        return map.get(addr);
    }

    @Override
    public void put(int addr, Object val) {
        map.put(addr, (T) val);
    }

    @Override
    public T deallocate(int addr) {
        return map.remove(addr);
    }

    @Override
    public Map<Integer, T> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, T> content) {
        map = (HashMap<Integer, T>) content;
    }

    @Override
    public String toString() {
        String s = "{";
        for (HashMap.Entry<Integer, T> entry : map.entrySet()) {
            s += entry.getKey().toString() + "->" + entry.getValue().toString() + "\n";
        }
        s += "}";
        return s;
    }
}
