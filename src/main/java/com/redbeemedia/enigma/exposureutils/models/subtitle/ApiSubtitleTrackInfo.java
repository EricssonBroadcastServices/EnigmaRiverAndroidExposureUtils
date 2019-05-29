package com.redbeemedia.enigma.exposureutils.models.subtitle;

import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.io.IOException;


public class ApiSubtitleTrackInfo implements Parcelable {
    private long fileSize;
    private String language;


    protected ApiSubtitleTrackInfo() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiSubtitleTrackInfo(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "fileSize":
                    this.fileSize = jsonReader.nextLong();
                    break;
                case "language":
                    this.language = jsonReader.nextString();
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public long getFileSize() {
        return this.fileSize;
    }

    public String getLanguage() {
        return this.language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiSubtitleTrackInfo> CREATOR = new Parcelable.Creator<ApiSubtitleTrackInfo>() {
        public ApiSubtitleTrackInfo createFromParcel(Parcel in) {
            ApiSubtitleTrackInfo object = new ApiSubtitleTrackInfo();
            object.fileSize = in.readLong();
            object.language = in.readString();
            return object;
        }

        public ApiSubtitleTrackInfo[] newArray(int size) {
            return new ApiSubtitleTrackInfo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(fileSize);
        dest.writeString(language);
    }
}
