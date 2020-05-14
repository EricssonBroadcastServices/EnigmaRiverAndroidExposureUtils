package com.redbeemedia.enigma.exposureutils.download;

import com.redbeemedia.enigma.exposureutils.models.asset.ApiAsset;

public class EnigmaDownloadHelper {
    public static boolean isAvaliableToDownload(ApiAsset apiAsset) {
        return !apiAsset.getDownloadBlocked();
    }
}
