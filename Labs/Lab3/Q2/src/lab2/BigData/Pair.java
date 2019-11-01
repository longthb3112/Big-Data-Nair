package lab2.BigData;

public class Pair<T> {
    public String getKey() {
        return key;
    }

    public Pair(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    private String key;
    private T value;
}
