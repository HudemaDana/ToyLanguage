package Model.Type;

import Model.Value.IntValue;

public class IntType implements Type {
    @Override
    public boolean equals(Object type) {
        if (type instanceof IntType)
            return true;
        return false;
    }

    @Override
    public Type clone() {
        return new IntType();
    }

    @Override
    public IntValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }


}
