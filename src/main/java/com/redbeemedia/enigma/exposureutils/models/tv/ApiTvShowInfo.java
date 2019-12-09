package com.redbeemedia.enigma.exposureutils.models.tv;

import com.redbeemedia.enigma.exposureutils.models.localized.ApiLocalizedData;
import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.util.List;
import java.io.IOException;
import com.redbeemedia.enigma.core.util.JsonReaderUtil;


public class ApiTvShowInfo implements Parcelable {
    private List<ApiLocalizedData> localizedData;


    protected ApiTvShowInfo() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiTvShowInfo(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "localizedData":
                    this.localizedData = JsonReaderUtil.readArray(jsonReader, ApiLocalizedData.class);
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public List<ApiLocalizedData> getLocalizedData() {
        return this.localizedData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiTvShowInfo> CREATOR = new Parcelable.Creator<ApiTvShowInfo>() {
        public ApiTvShowInfo createFromParcel(Parcel in) {
            ApiTvShowInfo object = new ApiTvShowInfo();
            object.localizedData = in.createTypedArrayList(ApiLocalizedData.CREATOR);
            return object;
        }

        public ApiTvShowInfo[] newArray(int size) {
            return new ApiTvShowInfo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(localizedData);
    }
}
