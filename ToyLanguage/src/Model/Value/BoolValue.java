package Model.Value;
import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value {
    boolean val;
    public BoolValue(boolean v){
        val=v;
    }
    public boolean getValue(){
        return val;
    }

    @Override
    public Value clone() {
        return new BoolValue(val);
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }

    @Override
    public Type getType(){
        return new BoolType();
    }
}
