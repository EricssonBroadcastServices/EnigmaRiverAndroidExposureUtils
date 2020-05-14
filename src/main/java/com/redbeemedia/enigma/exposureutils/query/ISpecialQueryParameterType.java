package com.redbeemedia.enigma.exposureutils.query;

import com.redbeemedia.enigma.core.util.IStringAppendable;

public interface ISpecialQueryParameterType {
    IStringAppendable appendTo(String name, IStringAppendable urlPath);
}
