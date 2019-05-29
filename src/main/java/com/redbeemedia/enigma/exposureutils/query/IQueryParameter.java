package com.redbeemedia.enigma.exposureutils.query;

import com.redbeemedia.enigma.core.util.IStringAppendable;

public interface IQueryParameter<T> {
    void setValue(T value);
    IStringAppendable apply(IStringAppendable appendable);
}
