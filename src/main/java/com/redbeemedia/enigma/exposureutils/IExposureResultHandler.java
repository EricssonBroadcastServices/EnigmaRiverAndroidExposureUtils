package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.error.EnigmaError;

public interface IExposureResultHandler<T> {
    void onSuccess(T result);
    void onError(EnigmaError error);
}
