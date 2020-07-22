package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.exposureutils.models.availability.ApiAvailabilityKeys;
import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.exposureutils.query.QueryParameterBuilder;
import com.redbeemedia.enigma.core.util.UrlPath;
import java.lang.String;
import com.redbeemedia.enigma.exposureutils.query.IQueryParameter;


public class GetAvailabilityKeys extends AbstractExposureRequest<ApiAvailabilityKeys> {
    private IQueryParameter<String> time = QueryParameterBuilder.create(qps, String.class, "time");

    public GetAvailabilityKeys(IExposureResultHandler<ApiAvailabilityKeys> resultHandler) {
        super("GET", parseObjectMethod(ApiAvailabilityKeys.class), resultHandler);
    }

    public GetAvailabilityKeys setTime(String time) {
        this.time.setValue(time);
        return this;
    }

    @Override
    public UrlPath getUrl(IBusinessUnit businessUnit) {
        return qps.applyAll(businessUnit.getApiBaseUrl("v2").append("entitlement/availabilitykey"));
    }
}
