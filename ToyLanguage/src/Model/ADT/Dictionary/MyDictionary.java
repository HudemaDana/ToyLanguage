package Model.ADT.Dictionary;


import Exception.VariableException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<T,E> implements MyIDictionary<T,E>{
    final HashMap<T, E> map;

    public MyDictionary(){
        map = new HashMap<>();
    }
    @Override
    public E lookup(T id) {
        return map.get(id);
    }

    @Override
    public Boolean isVariableDefined(T id){
        if(map.containsKey(id)){
            return true;
        }
        return false;
    }

    @Override
    public Map<T,E> getContent(){
        return map;
    }

    @Override
    public void add(T id,E elem) throws VariableException{
        if(isVariableDefined(id))
            throw new VariableException("Id already exists");
        map.put(id,elem);

    }

    @Override
    public void remove(T id) throws VariableException{
        if(!isVariableDefined(id))
            throw new VariableException("Id doesn't exist");
        map.remove(id);
    }

    @Override
    public void update(T id, E elem) throws VariableException{
        if(!isVariableDefined(id))
            throw new VariableException("Id can't be updated because it doesn't exist");
        map.replace(id,elem);
    }

    @Override
    public String toString(){
        StringBuilder symTblList = new StringBuilder();
        for(T var: map.keySet()){
            String str = var.toString()+" = "+map.get(var).toString()+"\n";
            symTblList.append(str);
        }
        return symTblList.toString();
    }
}
