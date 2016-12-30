package aueb.msc.cs.utils;

import java.io.BufferedReader;
import java.util.Map;

final class HmapEntry implements Map.Entry<BufferedReader, String[]> {
    private final BufferedReader key;
    private String[] value;

    public HmapEntry(BufferedReader key, String[] value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public BufferedReader getKey() {
        return key;
    }

    @Override
    public String[] getValue() {
        return value;
    }

    @Override
    public String[] setValue(String[] value) {
        String[] old = this.value;
        this.value = value;
        return old;
    }
}
