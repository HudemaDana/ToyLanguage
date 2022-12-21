package Model.Value;
import Model.Type.*;

public class IntValue implements Value {
    int val;
    public IntValue(int v){
        val=v;
    }
    public int getValue(){
        return val;
    }

    @Override
    public Value clone() {
        return new IntValue(val);
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }

    @Override
    public Type getType(){
        return new IntType();
    }
}
