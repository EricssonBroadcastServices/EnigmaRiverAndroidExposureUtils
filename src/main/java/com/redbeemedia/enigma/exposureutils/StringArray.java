package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.util.IStringAppendable;
import com.redbeemedia.enigma.exposureutils.query.ISpecialQueryParameterType;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class StringArray implements Iterable<String>, ISpecialQueryParameterType {
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
    public IStringAppendable appendTo(String name, IStringAppendable urlPath) {
        boolean first = true;
        for(String value : this) {
            if(first) {
                first = false;
            } else {
                urlPath = urlPath.append("&");
            }
            urlPath = urlPath.append(name).append("=").append(urlEncode(value));
        }
        return urlPath;
    }

    private static String urlEncode(Object value) {
        String stringValue = String.valueOf(value);
        try {
            return URLEncoder.encode(stringValue, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
