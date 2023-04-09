package serialization;

public class Main {
    public static void main(String[] args) {
        HelloData data = new HelloData(1, "Hello", "Transient");
        System.out.println("Before serialization: " + data);
        DataSerializer.serializeData(data);
        HelloData data2 = DataUnserializer.unSerializeData();
        System.out.println("After serialization: " + data2);
    }
}
