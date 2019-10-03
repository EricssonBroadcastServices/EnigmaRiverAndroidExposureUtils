package com.redbeemedia.enigma.exposureutils;

import android.util.JsonReader;

import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.core.error.EnigmaError;
import com.redbeemedia.enigma.core.http.IHttpCall;
import com.redbeemedia.enigma.core.session.ISession;
import com.redbeemedia.enigma.core.util.IInternalCallbackObject;
import com.redbeemedia.enigma.core.util.UrlPath;

public interface IExposureRequest<SuccessT> extends IInternalCallbackObject {
    void onSuccess(SuccessT obj);
    void onError(EnigmaError error);

    UrlPath getUrl(IBusinessUnit businessUnit);
    IHttpCall getHttpCall(ISession session);
    IHttpCall getHttpCall(IBusinessUnit businessUnit);
    IJsonParsingMethod<SuccessT> getJsonParsingMethod();

    interface IJsonParsingMethod<T> {
        T parse(JsonReader jsonReader) throws Exception;
    }
}
