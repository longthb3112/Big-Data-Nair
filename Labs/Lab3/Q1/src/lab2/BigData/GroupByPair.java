package lab2.BigData;

import java.util.List;

public class GroupByPair<T> {
    public String getKey() {
        return key;
    }

    public GroupByPair(String key, List<T> value) {
        this.key = key;
        this.value = value;
    }

    public List<T> getValue() {
        return value;
    }

    private String key;
    private List<T> value;
}
