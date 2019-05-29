package com.redbeemedia.enigma.exposureutils.query;

import com.redbeemedia.enigma.core.util.UrlPath;

public interface IQueryParameterSet {
    void add(IQueryParameter<?> queryParameter);
    UrlPath applyAll(UrlPath urlPath);
}
