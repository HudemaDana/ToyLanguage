package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value{

    String val;
    public StringValue(String v){ val = v;}
    public String getValue(){return val;}

    @Override
    public Value clone() {
        return new StringValue(val);
    }

    @Override
    public String toString() {
        return val;
    }

    @Override
    public Type getType() {
        return new StringType();
    }
}
