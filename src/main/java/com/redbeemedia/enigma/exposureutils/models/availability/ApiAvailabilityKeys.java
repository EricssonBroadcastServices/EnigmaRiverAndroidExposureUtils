package com.redbeemedia.enigma.exposureutils.models.availability;

import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.util.List;
import java.io.IOException;
import com.redbeemedia.enigma.core.util.JsonReaderUtil;


public class ApiAvailabilityKeys implements Parcelable {
    private String expiryDate;
    private List<String> availabilityKeys;


    protected ApiAvailabilityKeys() {}//Protected constructor for Parcelable.Creator and Mocks

    public ApiAvailabilityKeys(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "expiryDate":
                    this.expiryDate = jsonReader.nextString();
                    break;
                case "availabilityKeys":
                    this.availabilityKeys = JsonReaderUtil.readArray(jsonReader, String.class);
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public String getExpiryDate() {
        return this.expiryDate;
    }

    public List<String> getAvailabilityKeys() {
        return this.availabilityKeys;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ApiAvailabilityKeys> CREATOR = new Parcelable.Creator<ApiAvailabilityKeys>() {
        public ApiAvailabilityKeys createFromParcel(Parcel in) {
            ApiAvailabilityKeys object = new ApiAvailabilityKeys();
            object.expiryDate = in.readString();
            object.availabilityKeys = in.createStringArrayList();
            return object;
        }

        public ApiAvailabilityKeys[] newArray(int size) {
            return new ApiAvailabilityKeys[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(expiryDate);
        dest.writeStringList(availabilityKeys);
    }
}
