package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.error.EnigmaError;

public interface IExposureResultHandler<T> {
    /**
     * <p>Inspired by {@code org.hamcrest.Matcher} from JUnit lib.</p>
     * <br>
     * <p style="margin-left: 25px; font-weight:bold;">It's easy to ignore JavaDoc, but a bit harder to ignore compile errors .</p>
     * <p style="margin-left: 50px">-- Hamcrest source</p>
     */
    @Deprecated
    void _dont_implement_IExposureResultHandler___instead_extend_BaseExposureResultHandler_();

    void onSuccess(T result);
    void onError(EnigmaError error);
}
