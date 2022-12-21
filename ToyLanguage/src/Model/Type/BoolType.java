package Model.Type;

import Model.Value.BoolValue;

public class BoolType implements Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof BoolType)
            return true;
        return false;
    }

    @Override
    public Type clone(){
        return new BoolType();
    }

    @Override
    public BoolValue defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }
}
