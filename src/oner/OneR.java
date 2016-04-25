package oner;

import types.Attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Денис on 23.04.2016.
 */
public class OneR {

    HashMap<String, String> rules;
    ArrayList<String> classVal;

    public OneR() {
        rules = new HashMap<>();
        classVal = new ArrayList<>();
    }

    public HashMap<String, String> getRules() {
        return rules;
    }

    private Attribute getStrongestAttribute(HashMap<String, Collection<String>> dataset, String attributeName, ArrayList<Attribute> attributes) {
        classVal = (ArrayList<String>) dataset.get(attributeName);
        dataset.remove(attributeName);
        HashMap<String, HashMap<String, Integer>> statistic = new HashMap<>();

        Attribute strongestAttribute = null;

        for (Attribute a : attributes) {
            int i = 0;

            if (dataset.get(a.getName()) == null) continue;

            for (String s : dataset.get(a.getName())) {
                if (!statistic.containsKey(s)) {
                    statistic.put(s, new HashMap<>());
                    classVal.forEach(b -> {
                        statistic.get(s).put(b, 0);
                    });
                    statistic.get(s).put(classVal.get(i), 1);
                    i++;
                } else {
                    statistic.get(s).put(classVal.get(i), statistic.get(s).get(classVal.get(i)) + 1);
                    i++;
                }
            }
            statistic.keySet().forEach(r -> {
                final int[] max = {0};
                statistic.get(r).forEach((s, integer) -> {
                    if (statistic.get(r).get(s) > max[0]) {
                        rules.put(r, s);
                        max[0] = statistic.get(r).get(s);
                    }
                });
            });
            System.out.println("Attribute: " + a.getName() + " Incorrectly classified instances: " +
                    getAccuracy(rules, a, classVal, dataset));
            if (strongestAttribute == null) strongestAttribute = a;
            else {
                if (getAccuracy(rules, a, classVal, dataset) < getAccuracy(rules, strongestAttribute, classVal, dataset)) {
                    strongestAttribute = a;
                }
            }
        }
        return strongestAttribute;
    }

    public String get_1RForAttribute(HashMap<String, Collection<String>> dataset, String attributeName, ArrayList<Attribute> attributes) {
        Attribute strongestAttribute = getStrongestAttribute(dataset, attributeName, attributes);
        HashMap<String, String> rules = getRules();
        int positiveValue = 0;
        int i = 0;
        for (String s : dataset.get(strongestAttribute.getName())) {
            if (rules.get(s).equals(getClassVal().get(i))) {
                positiveValue++;
                i++;
            } else i++;
        }

        System.out.println("Strongest attribute is:" + strongestAttribute.getName());

        System.out.println("Rules:");
        strongestAttribute.getPossibleValues().forEach(a -> System.out.println("If " + a + " Then " + rules.get(a).toString()));

        String result = "Correctly classified instances:        "
                + positiveValue + "     "
                + ((double) positiveValue / (double) dataset.get(strongestAttribute.getName()).size()) * 100 + "%" + "\n";
        result += "Incorrect classified instances:        "
                + (dataset.get(strongestAttribute.getName()).size() - positiveValue)
                + "      "
                + (100 - ((double) positiveValue / (double) dataset.get(strongestAttribute.getName()).size()) * 100 + "%");
        return result;
    }


    private int getAccuracy(HashMap<String, String> rules, Attribute attribute,
                            ArrayList<String> classVal, HashMap<String, Collection<String>> dataset) {

        int i = 0;
        int correctlyClassified = 0;
        for (String s : dataset.get(attribute.getName())) {
            if (rules.get(s).equals(classVal.get(i))) {
                correctlyClassified++;
                i++;
            } else {
                i++;
            }
        }
        return classVal.size() - correctlyClassified;
    }

    public ArrayList<String> getClassVal() {
        return classVal;
    }
}
