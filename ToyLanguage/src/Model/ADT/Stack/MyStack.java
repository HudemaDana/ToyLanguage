package Model.ADT.Stack;

import Exception.EmptyCollectionException;

import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
     final Stack<T> stack;

     public MyStack(){
         stack = new Stack<>();
     }

     @Override
     public T pop() throws EmptyCollectionException {
         if(stack.isEmpty())
             throw new EmptyCollectionException("No elems on the stack");

         return stack.pop();
     }

     @Override
     public void push(T v){
         if(v!=null)
             stack.push(v);
     }

     @Override
    public T top() throws EmptyCollectionException {
         if(stack.isEmpty())
             throw new EmptyCollectionException("No elem on the stack");

         return stack.firstElement();
    }

    public int size(){
         return stack.size();
    }

    @Override
    public boolean isEmpty() {
         if(stack.size()!=0)
            return false;
         return true;
    }

    @Override
    public List getValues() {
        return stack.subList(0,stack.size());
    }

    @Override
    public String toString(){
         StringBuilder str = new StringBuilder("{ ");
         for(T elem:stack){
             str.insert(1,elem.toString()+" | ");
         }
         str.append(" }");
         return str.toString();
    }
}
