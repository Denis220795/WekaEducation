package reader;

import types.Attribute;

import java.io.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Денис on 18.04.2016.
 */
public class ARFFFileReader implements Reader {

    String relation;

    HashMap<String, Collection<String>> dataSet;

    ArrayList<Attribute> attributes;

    public ARFFFileReader() {
        dataSet = new HashMap<>();
        attributes = new ArrayList<>();
    }


    public void readFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        relation = br.readLine();
        final boolean[] hasData = {false};
        br.lines().forEach(a -> {
            if (a.contains("@relation")) {
                relation = a;
            } else {
                if (a.contains("@attribute")) {
                    String[] strings = a.split(" ");
                    Attribute attribute;
                    ArrayList possibleValues = new ArrayList<>();
                    if (strings[2].contains("{") | strings[2].contains("}")) {
                        strings[2] = strings[2].replaceAll("\\{", "");
                        strings[2] = strings[2].replaceAll("\\}", "");
                        String[] possibleValuesArr = strings[2].split(",");
                        for (int i = 0; i < possibleValuesArr.length; i++) {
                            possibleValues.add(possibleValuesArr[i]);
                        }
                        attribute = new Attribute(strings[1], "symbolic", possibleValues);
                        attributes.add(attribute);
                    } else {
                        attribute = new Attribute(strings[1], "numeric", null);
                        attributes.add(attribute);
                    }
                } else {
                    if (a.contains("@data")) {
                        hasData[0] = true;
                    }
                    if (hasData[0]) {
                        String[] atrValues = a.split(",");
                        if (atrValues.length == attributes.size()) {
                            for (int i = 0; i < atrValues.length; i++) {
                                if (dataSet.containsKey(attributes.get(i).getName())) {
                                    dataSet.get(attributes.get(i).getName()).add(atrValues[i]);
                                } else {
                                    ArrayList<String> values = new ArrayList<>();
                                    values.add(atrValues[i]);
                                    dataSet.put(attributes.get(i).getName(), values);
                                }
                            }
                        }
                    }

                }
            }
        });
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public HashMap<String, Collection<String>> getDataSet() {
        return dataSet;
    }

    public String getRelation() {
        return relation;
    }
}