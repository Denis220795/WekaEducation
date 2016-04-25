import oner.OneR;
import reader.ARFFFileReader;
import zeror.ZeroR;

import java.io.File;
import java.io.IOException;

/**
 * Created by Денис on 18.04.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        File f = new File("D:\\weather.nominal.arff");
        ARFFFileReader reader = new ARFFFileReader();
        reader.readFile(f);

        System.out.println("Attributes values: ");
        reader.getAttributes().forEach(a -> System.out.println(a.toString()));

        ZeroR _0R = new ZeroR();
        System.out.println("ZeroR result>> ");
        System.out.println(_0R.get_0RForAttribute(reader.getDataSet(), "play"));

        OneR _1R = new OneR();
        System.out.println("OneR result>> ");
        System.out.println(_1R.get_1RForAttribute(reader.getDataSet(), "play", reader.getAttributes()));
    }
}