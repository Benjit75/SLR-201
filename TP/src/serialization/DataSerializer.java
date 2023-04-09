package serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class DataSerializer {
    public static void serializeData(HelloData data) {
        try {
            FileOutputStream fout = new FileOutputStream("data/data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(data);
            out.close();
            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
