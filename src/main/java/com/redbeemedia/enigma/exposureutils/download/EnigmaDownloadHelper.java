package com.redbeemedia.enigma.exposureutils.download;

import com.redbeemedia.enigma.core.util.ISO8601Util;
import com.redbeemedia.enigma.exposureutils.models.asset.ApiAsset;
import com.redbeemedia.enigma.exposureutils.models.asset.ApiAssetRights;
import com.redbeemedia.enigma.exposureutils.models.publication.ApiPublication;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class EnigmaDownloadHelper {
    /**
     * Checks if an asset is available to download.
     *
     * @param apiAsset The asset to be checked
     * @param nowUtcMillis Milliseconds since the Epoch
     * @param userAvailabilityKeys Availability keys for the user (returned from entitlement/availabilitykey endpoint)
     * @return if the asset is available to download
     */
    public static boolean isAvailableToDownload(ApiAsset apiAsset, long nowUtcMillis, Collection<String> userAvailabilityKeys) {
        List<ApiPublication> assetPublications = apiAsset.getPublications();
        if(assetPublications != null) {
            List<ApiPublication> publications = new ArrayList<>(assetPublications);
            filterPublications(publications, nowUtcMillis, userAvailabilityKeys);
            if(publications.isEmpty()) {
                return false;
            }

            for(ApiPublication publication : publications) {
                ApiAssetRights rights = publication.getRights();
                if(rights == null || !rights.getDownloadBlocked()) {
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /*package-protected*/ static void filterPublications(List<ApiPublication> publications, long nowUtc, Collection<String> userAvailabilityKeys) {
        ISO8601Util.IISO8601Parser iso8601Parser = ISO8601Util.newParser();
        Iterator<ApiPublication> iterator = publications.iterator();
        while(iterator.hasNext()) {
            ApiPublication publication = iterator.next();
            if(!isActive(iso8601Parser, nowUtc, publication.getFromDate(), publication.getToDate())) {
                iterator.remove();
                continue;
            }
            if(isIntersectionEmpty(publication.getAvailabilityKeys(), userAvailabilityKeys)) {
                iterator.remove();
                continue;
            }
        }
    }

    /*package-protected*/ static boolean isActive(ISO8601Util.IISO8601Parser iso8601Parser, long nowUtc, String fromDate, String toDate) {
        boolean isAfterFrom = true;
        if(fromDate != null) {
            try {
                isAfterFrom = nowUtc >= iso8601Parser.parse(fromDate);
            } catch (ParseException e) {
                isAfterFrom = false;
            }
        }
        boolean isBeforeTo = true;
        if(toDate != null) {
            try {
                isBeforeTo = nowUtc <= iso8601Parser.parse(toDate);
            } catch (ParseException e) {
                isBeforeTo = false;
            }
        }
        return isAfterFrom && isBeforeTo;
    }

    /*package-protected*/ static boolean isIntersectionEmpty(Collection<String> a, Collection<String> b) {
        if(((a == null) || a.isEmpty()) || (b == null || b.isEmpty())) {
            return true;
        }
        for(String element : a) {
            if(b.contains(element)) {
                return false;
            }
        }
        return true;
    }
}
