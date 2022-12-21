package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value {
    int address;
    Type locationType;

    public RefValue(int addr, Type type) {
        address = addr;
        locationType = type;
    }


    public int getAddress() {
        return address;
    }

    public Type getLocationType(){
        return locationType;
    }

    @Override
    public Value clone() {
        return new RefValue(address,locationType);
    }

    @Override
    public Type getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + "," + locationType.toString() + ")";
    }
}
