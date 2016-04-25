package zeror;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by Денис on 22.04.2016.
 */
public class ZeroR {
    private String getMostPossibleAttribute(HashMap<String, Collection<String>> dataset, String attributeName) {

        HashMap<String, Integer> values = new HashMap<>();

        dataset.get(attributeName).forEach(a -> {
            if (!values.keySet().contains(a))
                values.put(a,0);
            else {
                int val = values.get(a)+1;
                values.replace(a,val);
            }
        });

        final String[] result = {""};
        final int[] num = {0};
        values.keySet().forEach(a -> {
            if (values.get(a)> num[0]) {
                num[0] =values.get(a);
                result[0] =a;
            }
        });
        return result[0];
    }

    public String get_0RForAttribute(HashMap<String, Collection<String>> dataset, String attributeName) {
        String mostPossibleAttribute = getMostPossibleAttribute(dataset, attributeName);
        final int[] positiveValue = {0};
        dataset.get(attributeName).forEach(a->{
            if (a.equals(mostPossibleAttribute)) {
                positiveValue[0]++;
            }
        });
        String result = "Correctly classified instances:        "
                +positiveValue[0]+"     "
                +((double)positiveValue[0]/(double)dataset.get(attributeName).size())*100+"%"+"\n";
        result += "Incorrect classified instances:        "
                +(dataset.get(attributeName).size()-positiveValue[0])
                +"      "
                +(100-((double)positiveValue[0]/(double)dataset.get(attributeName).size())*100+"%");
        return result;
    }
}