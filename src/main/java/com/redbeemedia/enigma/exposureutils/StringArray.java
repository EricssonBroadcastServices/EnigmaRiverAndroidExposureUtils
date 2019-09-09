package com.redbeemedia.enigma.exposureutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringArray implements Iterable<String> {
    private final List<String> list;

    public StringArray(List<String> list) {
        this.list = list;
    }

    public StringArray(String ... array) {
        this.list = new ArrayList<>(Arrays.asList(array));
    }

    public StringArray() {
        this(new ArrayList<>());
    }

    @Override
    public Iterator<String> iterator() {
        return list.iterator();
    }

    public int size() {
        return list.size();
    }

    public String get(int index) {
        return list.get(index);
    }

    public StringArray add(String value) {
        list.add(value);
        return this;
    }

    @Override
    public String toString() {
        //TODO evaluate if this is the expected format for array[string] in swagger
        return list.toString();
    }
}
