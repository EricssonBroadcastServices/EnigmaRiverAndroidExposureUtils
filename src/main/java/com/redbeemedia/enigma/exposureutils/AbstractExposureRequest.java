package com.redbeemedia.enigma.exposureutils;

import android.util.JsonReader;

import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.core.error.Error;
import com.redbeemedia.enigma.core.http.AuthenticatedExposureApiCall;
import com.redbeemedia.enigma.core.http.ExposureApiCall;
import com.redbeemedia.enigma.core.http.IHttpCall;
import com.redbeemedia.enigma.core.session.ISession;
import com.redbeemedia.enigma.core.util.JsonReaderUtil;
import com.redbeemedia.enigma.exposureutils.query.IQueryParameterSet;
import com.redbeemedia.enigma.exposureutils.query.QueryParameterSet;

import java.lang.reflect.Constructor;
import java.util.List;

/*package-protected*/ abstract class AbstractExposureRequest<SuccessT> implements IExposureRequest<SuccessT> {
    protected IQueryParameterSet qps = new QueryParameterSet();

    private final String requestMethod;
    private final IJsonParsingMethod<SuccessT> parsingMethod;
    private final IExposureResultHandler<SuccessT> resultHandler;

    public AbstractExposureRequest(String requestMethod, IJsonParsingMethod<SuccessT> parsingMethod, IExposureResultHandler<SuccessT> resultHandler) {
        this.requestMethod = requestMethod;
        this.parsingMethod = parsingMethod;
        this.resultHandler = resultHandler;
    }


    @Override
    public final void onSuccess(SuccessT obj) {
//        try {
            resultHandler.onSuccess(obj);
//        } catch (Exception e) {
//            onError(Error.UNEXPECTED_ERROR);
//        }
    }

    @Override
    public final void onError(Error error) {
        resultHandler.onError(error);
    }

    @Override
    public IHttpCall getHttpCall(ISession session) {
        return new AuthenticatedExposureApiCall(requestMethod,session);
    }

    @Override
    public IHttpCall getHttpCall(IBusinessUnit businessUnit) {
        return new ExposureApiCall(requestMethod);
    }

    @Override
    public IJsonParsingMethod<SuccessT> getJsonParsingMethod() {
        return parsingMethod;
    }


    /*package-protected*/ static <T> IExposureRequest.IJsonParsingMethod<List<T>> parseListMethod(final Class<T> elementType) {
        return new IExposureRequest.IJsonParsingMethod<List<T>>() {
            @Override
            public List<T> parse(JsonReader jsonReader) throws Exception {
                return JsonReaderUtil.readArray(jsonReader, elementType);
            }
        };
    }

    /*package-protected*/ static <T> IExposureRequest.IJsonParsingMethod<T> parseObjectMethod(Class<T> objectType) {
        try {
            final Constructor<T> constructor = objectType.getConstructor(JsonReader.class);
            return new IExposureRequest.IJsonParsingMethod<T>() {
                @Override
                public T parse(JsonReader jsonReader) throws Exception {
                    return constructor.newInstance(jsonReader);
                }
            };
        } catch (final NoSuchMethodException e) {
            return new IExposureRequest.IJsonParsingMethod<T>() {
                @Override
                public T parse(JsonReader jsonReader) throws Exception {
                    throw e;
                }
            };
        }
    }
}
