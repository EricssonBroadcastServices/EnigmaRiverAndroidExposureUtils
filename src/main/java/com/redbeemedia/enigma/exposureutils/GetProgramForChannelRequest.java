package com.redbeemedia.enigma.exposureutils;

import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.exposureutils.models.program.ApiProgramResponse;
import com.redbeemedia.enigma.exposureutils.query.IQueryParameter;
import com.redbeemedia.enigma.exposureutils.query.QueryParameterBuilder;
import com.redbeemedia.enigma.core.util.UrlPath;

public class GetProgramForChannelRequest extends AbstractExposureRequest<ApiProgramResponse> {
    private String channelId;
    private String programId;
    private IQueryParameter<Boolean> includeUserData = QueryParameterBuilder.create(qps, Boolean.class, "includeUserData");

    public GetProgramForChannelRequest(String channelId, String programId, IExposureResultHandler<ApiProgramResponse> resultHandler) {
        super("GET", parseObjectMethod(ApiProgramResponse.class), resultHandler);
        this.channelId = channelId;
        this.programId = programId;
    }


    public GetProgramForChannelRequest setIncludeUserData(boolean includeUserData) {
        this.includeUserData.setValue(includeUserData);
        return this;
    }

    @Override
    public UrlPath getUrl(IBusinessUnit businessUnit) {
        return qps.applyAll(businessUnit.getApiBaseUrl().append("epg").append(channelId).append("program").append(programId));
    }
}
