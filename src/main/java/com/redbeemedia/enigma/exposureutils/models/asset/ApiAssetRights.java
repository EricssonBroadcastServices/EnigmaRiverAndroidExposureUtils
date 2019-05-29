package com.redbeemedia.enigma.exposureutils.models.asset;

import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.io.IOException;


public class ApiAssetRights implements Parcelable {
    private boolean jailbrokenBlocked;
    private boolean downloadBlocked;
    private long minBitrate;
    private boolean streamingBlocked;
    private boolean threeGBlocked;
    private long downloadMaxSecondsAfterPlay;
    private long maxBitrate;
    private boolean sessionShiftEnabled;
    private boolean rwEnabled;
    private boolean amcDebugLogEnabled;
    private long downloadMaxSecondsAfterDownload;
    private boolean locationEnabled;
    private boolean analyticsEnabled;
    private long maxAds;
    private boolean HDMIBlocked;
    private boolean ffEnabled;
    private long minPlayPosition;
    private long maxResHeight;
    private boolean wifiBlocked;
    private boolean fourGBlocked;
    private long maxFileSize;
    private long playCount;
    private boolean airplayBlocked;
    private long maxPlayPosition;
    private String expiration;
    private String activation;
    private long maxResWidth;


    protected ApiAssetRights() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiAssetRights(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "jailbrokenBlocked":
                    this.jailbrokenBlocked = jsonReader.nextBoolean();
                    break;
                case "downloadBlocked":
                    this.downloadBlocked = jsonReader.nextBoolean();
                    break;
                case "minBitrate":
                    this.minBitrate = jsonReader.nextLong();
                    break;
                case "streamingBlocked":
                    this.streamingBlocked = jsonReader.nextBoolean();
                    break;
                case "threeGBlocked":
                    this.threeGBlocked = jsonReader.nextBoolean();
                    break;
                case "downloadMaxSecondsAfterPlay":
                    this.downloadMaxSecondsAfterPlay = jsonReader.nextLong();
                    break;
                case "maxBitrate":
                    this.maxBitrate = jsonReader.nextLong();
                    break;
                case "sessionShiftEnabled":
                    this.sessionShiftEnabled = jsonReader.nextBoolean();
                    break;
                case "rwEnabled":
                    this.rwEnabled = jsonReader.nextBoolean();
                    break;
                case "amcDebugLogEnabled":
                    this.amcDebugLogEnabled = jsonReader.nextBoolean();
                    break;
                case "downloadMaxSecondsAfterDownload":
                    this.downloadMaxSecondsAfterDownload = jsonReader.nextLong();
                    break;
                case "locationEnabled":
                    this.locationEnabled = jsonReader.nextBoolean();
                    break;
                case "analyticsEnabled":
                    this.analyticsEnabled = jsonReader.nextBoolean();
                    break;
                case "maxAds":
                    this.maxAds = jsonReader.nextLong();
                    break;
                case "HDMIBlocked":
                    this.HDMIBlocked = jsonReader.nextBoolean();
                    break;
                case "ffEnabled":
                    this.ffEnabled = jsonReader.nextBoolean();
                    break;
                case "minPlayPosition":
                    this.minPlayPosition = jsonReader.nextLong();
                    break;
                case "maxResHeight":
                    this.maxResHeight = jsonReader.nextLong();
                    break;
                case "wifiBlocked":
                    this.wifiBlocked = jsonReader.nextBoolean();
                    break;
                case "fourGBlocked":
                    this.fourGBlocked = jsonReader.nextBoolean();
                    break;
                case "maxFileSize":
                    this.maxFileSize = jsonReader.nextLong();
                    break;
                case "playCount":
                    this.playCount = jsonReader.nextLong();
                    break;
                case "airplayBlocked":
                    this.airplayBlocked = jsonReader.nextBoolean();
                    break;
                case "maxPlayPosition":
                    this.maxPlayPosition = jsonReader.nextLong();
                    break;
                case "expiration":
                    this.expiration = jsonReader.nextString();
                    break;
                case "activation":
                    this.activation = jsonReader.nextString();
                    break;
                case "maxResWidth":
                    this.maxResWidth = jsonReader.nextLong();
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public boolean getJailbrokenBlocked() {
        return this.jailbrokenBlocked;
    }

    public boolean getDownloadBlocked() {
        return this.downloadBlocked;
    }

    public long getMinBitrate() {
        return this.minBitrate;
    }

    public boolean getStreamingBlocked() {
        return this.streamingBlocked;
    }

    public boolean getThreeGBlocked() {
        return this.threeGBlocked;
    }

    public long getDownloadMaxSecondsAfterPlay() {
        return this.downloadMaxSecondsAfterPlay;
    }

    public long getMaxBitrate() {
        return this.maxBitrate;
    }

    public boolean getSessionShiftEnabled() {
        return this.sessionShiftEnabled;
    }

    public boolean getRwEnabled() {
        return this.rwEnabled;
    }

    public boolean getAmcDebugLogEnabled() {
        return this.amcDebugLogEnabled;
    }

    public long getDownloadMaxSecondsAfterDownload() {
        return this.downloadMaxSecondsAfterDownload;
    }

    public boolean getLocationEnabled() {
        return this.locationEnabled;
    }

    public boolean getAnalyticsEnabled() {
        return this.analyticsEnabled;
    }

    public long getMaxAds() {
        return this.maxAds;
    }

    public boolean getHDMIBlocked() {
        return this.HDMIBlocked;
    }

    public boolean getFfEnabled() {
        return this.ffEnabled;
    }

    public long getMinPlayPosition() {
        return this.minPlayPosition;
    }

    public long getMaxResHeight() {
        return this.maxResHeight;
    }

    public boolean getWifiBlocked() {
        return this.wifiBlocked;
    }

    public boolean getFourGBlocked() {
        return this.fourGBlocked;
    }

    public long getMaxFileSize() {
        return this.maxFileSize;
    }

    public long getPlayCount() {
        return this.playCount;
    }

    public boolean getAirplayBlocked() {
        return this.airplayBlocked;
    }

    public long getMaxPlayPosition() {
        return this.maxPlayPosition;
    }

    public String getExpiration() {
        return this.expiration;
    }

    public String getActivation() {
        return this.activation;
    }

    public long getMaxResWidth() {
        return this.maxResWidth;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiAssetRights> CREATOR = new Parcelable.Creator<ApiAssetRights>() {
        public ApiAssetRights createFromParcel(Parcel in) {
            ApiAssetRights object = new ApiAssetRights();
            object.jailbrokenBlocked = (in.readInt() != 0);
            object.downloadBlocked = (in.readInt() != 0);
            object.minBitrate = in.readLong();
            object.streamingBlocked = (in.readInt() != 0);
            object.threeGBlocked = (in.readInt() != 0);
            object.downloadMaxSecondsAfterPlay = in.readLong();
            object.maxBitrate = in.readLong();
            object.sessionShiftEnabled = (in.readInt() != 0);
            object.rwEnabled = (in.readInt() != 0);
            object.amcDebugLogEnabled = (in.readInt() != 0);
            object.downloadMaxSecondsAfterDownload = in.readLong();
            object.locationEnabled = (in.readInt() != 0);
            object.analyticsEnabled = (in.readInt() != 0);
            object.maxAds = in.readLong();
            object.HDMIBlocked = (in.readInt() != 0);
            object.ffEnabled = (in.readInt() != 0);
            object.minPlayPosition = in.readLong();
            object.maxResHeight = in.readLong();
            object.wifiBlocked = (in.readInt() != 0);
            object.fourGBlocked = (in.readInt() != 0);
            object.maxFileSize = in.readLong();
            object.playCount = in.readLong();
            object.airplayBlocked = (in.readInt() != 0);
            object.maxPlayPosition = in.readLong();
            object.expiration = in.readString();
            object.activation = in.readString();
            object.maxResWidth = in.readLong();
            return object;
        }

        public ApiAssetRights[] newArray(int size) {
            return new ApiAssetRights[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(jailbrokenBlocked ? 1 : 0);
        dest.writeInt(downloadBlocked ? 1 : 0);
        dest.writeLong(minBitrate);
        dest.writeInt(streamingBlocked ? 1 : 0);
        dest.writeInt(threeGBlocked ? 1 : 0);
        dest.writeLong(downloadMaxSecondsAfterPlay);
        dest.writeLong(maxBitrate);
        dest.writeInt(sessionShiftEnabled ? 1 : 0);
        dest.writeInt(rwEnabled ? 1 : 0);
        dest.writeInt(amcDebugLogEnabled ? 1 : 0);
        dest.writeLong(downloadMaxSecondsAfterDownload);
        dest.writeInt(locationEnabled ? 1 : 0);
        dest.writeInt(analyticsEnabled ? 1 : 0);
        dest.writeLong(maxAds);
        dest.writeInt(HDMIBlocked ? 1 : 0);
        dest.writeInt(ffEnabled ? 1 : 0);
        dest.writeLong(minPlayPosition);
        dest.writeLong(maxResHeight);
        dest.writeInt(wifiBlocked ? 1 : 0);
        dest.writeInt(fourGBlocked ? 1 : 0);
        dest.writeLong(maxFileSize);
        dest.writeLong(playCount);
        dest.writeInt(airplayBlocked ? 1 : 0);
        dest.writeLong(maxPlayPosition);
        dest.writeString(expiration);
        dest.writeString(activation);
        dest.writeLong(maxResWidth);
    }
}
