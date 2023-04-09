package serialization;

public class HelloData implements java.io.Serializable {
    private int number;
    private String message;

    private transient String transientMessage;

    public HelloData() {
        number = 0;
        message = "Hello";
        transientMessage = "Transient";
    }

    public HelloData(int number, String message, String transientMessage) {
        this.number = number;
        this.message = message;
        this.transientMessage = transientMessage;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTransientMessage() {
        return transientMessage;
    }

    public void setTransientMessage(String transientMessage) {
        this.transientMessage = transientMessage;
    }

    public String toString() {
        return "HelloData [number=" + number + ", message=" + message + ", transientMessage=" + transientMessage + "]";
    }
}
