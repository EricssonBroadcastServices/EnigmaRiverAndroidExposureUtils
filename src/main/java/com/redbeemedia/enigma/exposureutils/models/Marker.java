package com.redbeemedia.enigma.exposureutils.models;

import android.os.Parcel;
import android.util.JsonReader;
import android.os.Parcelable;
import java.io.IOException;


public class Marker implements Parcelable {
    private long offset;
    private String adMarkerType;
    private String url;


    protected Marker() {}//Protected constructor for Parcelable.Creator and Mocks

    public Marker(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "offset":
                    this.offset = jsonReader.nextLong();
                    break;
                case "adMarkerType":
                    this.adMarkerType = jsonReader.nextString();
                    break;
                case "url":
                    this.url = jsonReader.nextString();
                    break;
                default:
                    jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
    }


    public long getOffset() {
        return this.offset;
    }

    public String getAdMarkerType() {
        return this.adMarkerType;
    }

    public String getUrl() {
        return this.url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Marker> CREATOR = new Parcelable.Creator<Marker>() {
        public Marker createFromParcel(Parcel in) {
            Marker object = new Marker();
            object.offset = in.readLong();
            object.adMarkerType = in.readString();
            object.url = in.readString();
            return object;
        }

        public Marker[] newArray(int size) {
            return new Marker[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(offset);
        dest.writeString(adMarkerType);
        dest.writeString(url);
    }
}
