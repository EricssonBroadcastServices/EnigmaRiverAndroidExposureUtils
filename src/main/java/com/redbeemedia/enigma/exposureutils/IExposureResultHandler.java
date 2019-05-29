package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.error.Error;

public interface IExposureResultHandler<T> {
    void onSuccess(T result);
    void onError(Error error);
}
