package Model.ADT.List;

import java.util.LinkedList;

public class MyList<T> implements MyIList<T>{
    final LinkedList<T> list;

    public MyList(){
        list = new LinkedList<T>();
    }

    @Override
    public LinkedList<T> getContent(){
        return list;
    }

    @Override
    public boolean isEmpty(){
        if(list.size()!=0)
            return true;
        return false;
    }

    @Override
    public void addElem(T elem){
        list.add(elem);
    }

    @Override
    public void removeElem(T elem){
        list.remove(elem);
    }

    @Override
    public String toString(){
        StringBuilder outList = new StringBuilder();
        for(T i:list){
            if(i != null) {
                outList.append(i);
                outList.append("\n");
            }
        }

        return outList.toString();
    }
}
