package com.redbeemedia.enigma.exposureutils;

import java.lang.Integer;
import com.redbeemedia.enigma.core.businessunit.IBusinessUnit;
import com.redbeemedia.enigma.core.session.ISession;
import com.redbeemedia.enigma.exposureutils.query.QueryParameterBuilder;
import com.redbeemedia.enigma.core.util.UrlPath;
import com.redbeemedia.enigma.core.http.IHttpCall;
import com.redbeemedia.enigma.exposureutils.models.asset.ApiAsset;
import java.lang.String;
import java.lang.Boolean;
import com.redbeemedia.enigma.exposureutils.query.IQueryParameter;


public class GetAssetByIdRequest extends AbstractExposureRequest<ApiAsset> {
    private final String assetId;
    private IQueryParameter<AssetType> assetType = QueryParameterBuilder.create(qps, AssetType.class, "assetType");
    private IQueryParameter<String> sort = QueryParameterBuilder.create(qps, String.class, "sort");
    private IQueryParameter<String> query = QueryParameterBuilder.create(qps, String.class, "query");
    private IQueryParameter<StringArray> assetIds = QueryParameterBuilder.create(qps, StringArray.class, "assetIds");
    private IQueryParameter<String> parentalRatings = QueryParameterBuilder.create(qps, String.class, "parentalRatings");
    private IQueryParameter<Integer> pageSize = QueryParameterBuilder.create(qps, Integer.class, "pageSize", 50);
    private IQueryParameter<Integer> pageNumber = QueryParameterBuilder.create(qps, Integer.class, "pageNumber", 1);
    private IQueryParameter<FieldSet> fieldSet = QueryParameterBuilder.create(qps, FieldSet.class, "fieldSet", FieldSet.PARTIAL);
    private IQueryParameter<String> excludeFields = QueryParameterBuilder.create(qps, String.class, "excludeFields");
    private IQueryParameter<String> includeFields = QueryParameterBuilder.create(qps, String.class, "includeFields");
    private IQueryParameter<Boolean> onlyPublished = QueryParameterBuilder.create(qps, Boolean.class, "onlyPublished", true);
    private IQueryParameter<String> service = QueryParameterBuilder.create(qps, String.class, "service");
    private IQueryParameter<String> allowedCountry = QueryParameterBuilder.create(qps, String.class, "allowedCountry");
    private IQueryParameter<DeviceType> deviceType = QueryParameterBuilder.create(qps, DeviceType.class, "deviceType");
    private IQueryParameter<String> deviceQuery = QueryParameterBuilder.create(qps, String.class, "deviceQuery");
    private IQueryParameter<String> publicationQuery = QueryParameterBuilder.create(qps, String.class, "publicationQuery");
    private IQueryParameter<StringArray> products = QueryParameterBuilder.create(qps, StringArray.class, "products");
    private IQueryParameter<String> missingFieldsFilter = QueryParameterBuilder.create(qps, String.class, "missingFieldsFilter");
    private IQueryParameter<Boolean> includeUserData = QueryParameterBuilder.create(qps, Boolean.class, "includeUserData", false);

    public GetAssetByIdRequest(String assetId, IExposureResultHandler<ApiAsset> resultHandler) {
        super("GET", parseObjectMethod(ApiAsset.class), resultHandler);
        this.assetId = assetId;
    }

    public GetAssetByIdRequest setAssetType(AssetType assetType) {
        this.assetType.setValue(assetType);
        return this;
    }

    public GetAssetByIdRequest setSort(String sort) {
        this.sort.setValue(sort);
        return this;
    }

    public GetAssetByIdRequest setQuery(String query) {
        this.query.setValue(query);
        return this;
    }

    public GetAssetByIdRequest setAssetIds(StringArray assetIds) {
        this.assetIds.setValue(assetIds);
        return this;
    }

    public GetAssetByIdRequest setParentalRatings(String parentalRatings) {
        this.parentalRatings.setValue(parentalRatings);
        return this;
    }

    public GetAssetByIdRequest setPageSize(int pageSize) {
        this.pageSize.setValue(pageSize);
        return this;
    }

    public GetAssetByIdRequest setPageNumber(int pageNumber) {
        this.pageNumber.setValue(pageNumber);
        return this;
    }

    public GetAssetByIdRequest setFieldSet(FieldSet fieldSet) {
        this.fieldSet.setValue(fieldSet);
        return this;
    }

    public GetAssetByIdRequest setExcludeFields(String excludeFields) {
        this.excludeFields.setValue(excludeFields);
        return this;
    }

    public GetAssetByIdRequest setIncludeFields(String includeFields) {
        this.includeFields.setValue(includeFields);
        return this;
    }

    public GetAssetByIdRequest setOnlyPublished(boolean onlyPublished) {
        this.onlyPublished.setValue(onlyPublished);
        return this;
    }

    public GetAssetByIdRequest setService(String service) {
        this.service.setValue(service);
        return this;
    }

    public GetAssetByIdRequest setAllowedCountry(String allowedCountry) {
        this.allowedCountry.setValue(allowedCountry);
        return this;
    }

    public GetAssetByIdRequest setDeviceType(DeviceType deviceType) {
        this.deviceType.setValue(deviceType);
        return this;
    }

    public GetAssetByIdRequest setDeviceQuery(String deviceQuery) {
        this.deviceQuery.setValue(deviceQuery);
        return this;
    }

    public GetAssetByIdRequest setPublicationQuery(String publicationQuery) {
        this.publicationQuery.setValue(publicationQuery);
        return this;
    }

    public GetAssetByIdRequest setProducts(StringArray products) {
        this.products.setValue(products);
        return this;
    }

    public GetAssetByIdRequest setMissingFieldsFilter(String missingFieldsFilter) {
        this.missingFieldsFilter.setValue(missingFieldsFilter);
        return this;
    }

    public GetAssetByIdRequest setIncludeUserData(boolean includeUserData) {
        this.includeUserData.setValue(includeUserData);
        return this;
    }

    @Override
    public UrlPath getUrl(IBusinessUnit businessUnit) {
        return qps.applyAll(businessUnit.getApiBaseUrl("v1").append("content/asset").append(this.assetId));
    }

    @Override
    public IHttpCall getHttpCall(ISession session) {
        return getHttpCall(session.getBusinessUnit());
    }
}
