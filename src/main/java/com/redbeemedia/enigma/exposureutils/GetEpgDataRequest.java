package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.exposureutils.models.channel.ApiChannelEPGResponse;
import com.redbeemedia.enigma.exposureutils.query.IQueryParameter;
import com.redbeemedia.enigma.exposureutils.query.QueryParameterBuilder;
import com.redbeemedia.enigma.core.util.UrlPath;

import java.util.List;

//TODO use the new endpoint that takes 'daysBackward' and 'daysForward' instead. Don't provide this one, it is not very cachable
public class GetEpgDataRequest extends AbstractExposureRequest<List<ApiChannelEPGResponse>> {
    private IQueryParameter<Long> fromMillis = QueryParameterBuilder.create(qps, Long.class, "from");
    private IQueryParameter<Long> toMillis = QueryParameterBuilder.create(qps, Long.class, "to");
    private IQueryParameter<Boolean> includeUserData = QueryParameterBuilder.create(qps, Boolean.class, "includeUserData");

    public GetEpgDataRequest(long fromMillis, long toMillis, IExposureResultHandler<List<ApiChannelEPGResponse>> resultHandler) {
        super("GET", parseListMethod(ApiChannelEPGResponse.class), resultHandler);
        this.fromMillis.setValue(fromMillis);
        this.toMillis.setValue(toMillis);
    }

    public GetEpgDataRequest setIncludeUserData(boolean includeUserData) {
        this.includeUserData.setValue(includeUserData);
        return this;
    }

    @Override
    public UrlPath getUrl(IBusinessUnit businessUnit) {
        return qps.applyAll(businessUnit.getApiBaseUrl().append("epg"));
    }
}
