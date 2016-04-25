package types;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Денис on 18.04.2016.
 */
public class Attribute {

    String name;
    Collection possibleValues;
    String type;

    public Attribute(String name, String type, Collection possibleValues) {
        this.name = name;
        this.type = type;
        switch (type) {
            case "numeric": {
                this.possibleValues = new ArrayList<Double>();
                this.possibleValues = possibleValues;
                break;
            }
            case "symbolic": {
                this.possibleValues = new ArrayList<String>();
                this.possibleValues.addAll(possibleValues);
                break;
            }
            default: {
                this.type = null;
                this.possibleValues=possibleValues;
            }
        }
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", possibleValues=" + possibleValues +
                ", type='" + type + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public Collection getPossibleValues() {
        return possibleValues;
    }

    public String getType() {
        return type;
    }
}