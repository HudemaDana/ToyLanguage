package Model.Type;

import Model.Value.StringValue;
import Model.Value.Value;

public class StringType implements Type {

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StringType)
            return true;
        return false;
    }

    @Override
    public Type clone() {
        return new StringType();
    }

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "String";
    }
}
