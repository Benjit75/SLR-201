package serialization;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DataUnserializer {
    public static HelloData unSerializeData() {
        HelloData data = null;
        try {
            FileInputStream fin = new FileInputStream("data/data.ser");
            ObjectInputStream in = new ObjectInputStream(fin);
            data = (HelloData) in.readObject();
            in.close();
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
