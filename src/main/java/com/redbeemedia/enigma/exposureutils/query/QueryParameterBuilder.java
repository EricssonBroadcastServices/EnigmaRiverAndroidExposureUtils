package com.redbeemedia.enigma.exposureutils.query;

import com.redbeemedia.enigma.core.util.IStringAppendable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryParameterBuilder {
    public static <T> IQueryParameter<T> create(IQueryParameterSet owner, Class<T> type, String name, T defaultValue) {
        return create(owner, type,name, defaultValue, true);
    }

    public static <T> IQueryParameter<T> create(IQueryParameterSet owner, Class<T> type, String name) {
        return create(owner, type,name, null, false);
    }

    private static <T> IQueryParameter<T> create(IQueryParameterSet owner, Class<T> type, String name, T defaultValue, boolean usesDefault) {
        IQueryParameter<T> queryParameter = new IQueryParameter<T>() {
            private T value = defaultValue;
            private boolean hasBeenSet = usesDefault;

            @Override
            public void setValue(T value) {
                this.value = value;
                hasBeenSet = true;
            }

            @Override
            public IStringAppendable apply(IStringAppendable urlPath) {
                if(hasBeenSet) {
                    return urlPath.append(name).append("=").append(urlEncode(value));
                } else {
                    return urlPath;
                }
            }
        };
        owner.add(queryParameter);
        return queryParameter;
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
